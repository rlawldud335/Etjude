import boto3
import logging
import os
from botocore.exceptions import ClientError
from moviepy.editor import *
from dotenv import load_dotenv

load_dotenv()

def s3_connection():
    try:
        s3 = boto3.client(
            service_name="s3",
            region_name="ap-northeast-2",
            aws_access_key_id = os.getenv("aws_access_key_id"),
            aws_secret_access_key = os.getenv("aws_secret_access_key")
        )
    except Exception as e:
        print(e)
    else:
        print("s3 bucket connected!")
        return s3

def get_all_objects(s3, **base_kwargs):
    continuation_token = None
    while True:
        list_kwargs = dict(MaxKeys=1000, **base_kwargs)
        if continuation_token:
            list_kwargs['ContinuationToken'] = continuation_token
        response = s3.list_objects_v2(**list_kwargs)
        yield from response.get('Contents', [])
        if not response.get('IsTruncated'):  
            break
        continuation_token = response.get('NextContinuationToken')

def make_objects_list(s3, BucketName):
    with open("output.txt", "w") as f:
        for file in get_all_objects(s3, Bucket=BucketName):
            f.write(file['Key']+'\n')

    with open('./output.txt', 'r') as f:
        objects_list = f.readlines()
        for idx, obj in enumerate(objects_list):
            objects_list[idx] = obj.strip()
    return objects_list

def make_object(bucketname, objectname):
    s3_resource = boto3.resource(
                    service_name="s3",
                    region_name="ap-northeast-2", 
                    aws_access_key_id = os.getenv("aws_access_key_id"),
                    aws_secret_access_key = os.getenv("aws_secret_access_key")
                    )
    bucket = s3_resource.Bucket(bucketname)
    return bucket.Object(objectname)

def get_object_url(s3, object):
    filename = object.key
    bucketname = object.bucket_name
    location = s3.get_bucket_location(Bucket=bucketname)["LocationConstraint"]

    return f"https://{bucketname}.s3.{location}.amazonaws.com/{filename}"

def upload_file(s3, file_name, bucket, object_name=None):
    if object_name is None:
        object_name = os.path.basename(file_name)

    # Upload the file
    content_Type = 'mp4'
    try:
        if 'mp4' in file_name:
            content_Type = 'mp4'
        response = s3.upload_file(
            file_name,
            bucket,
            object_name,
            ExtraArgs={"ContentType": content_Type}
            )
    except ClientError as e:
        logging.error(e)
        return False
    return True

def search_user_scene(studio_id, object_list):
    target = str(studio_id) + "/"
    user_scense = []
    user_scense = [x[len(studio_id):] for x in object_list if target in x]
    story_id = user_scense[0][1]
    return user_scense[:], story_id

def search_origin_scene(story_id, object_list):
    target = "scenes/" + str(story_id)
    scenes = []
    for obj in object_list:
        if target in obj:
            scenes.append(obj)
    return scenes

def make_film_scene(studio_id, object_list):
    film_scene = []
    user_scenes, story_id = search_user_scene(studio_id, object_list)
    origin_scenes = search_origin_scene(story_id, object_list)
    for org_scene in origin_scenes:
        if(org_scene[6:-4] + ".webm" not in user_scenes):
            film_scene.append(org_scene)
        else:
            film_scene.append(studio_id + org_scene[6:-4] + ".webm")
    return film_scene

def make_film(s3, film_scene, bucket_name, studio_id, film_name):
    obj_list = []
    for scene in film_scene:
        obj_list.append(VideoFileClip(get_object_url(s3, make_object(bucket_name, scene))))
    video = concatenate_videoclips(obj_list[:])
    video.write_videofile(film_name+ ".mp4")
    upload_file(s3, "./" + film_name + ".mp4", bucket_name, studio_id + "/" + film_name + ".mp4")
    res = s3.list_objects_v2(Bucket=bucket_name, Prefix= studio_id + "/" + film_name, MaxKeys=1)["Contents"][0]["Key"]
    url = get_object_url(s3, make_object(bucket_name, res))
    return url


