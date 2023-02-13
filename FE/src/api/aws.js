/* eslint-disable no-undef */
/* eslint-disable import/no-extraneous-dependencies */
import aws from "aws-sdk";
import { apiInstance } from "@/api/index";

const api = apiInstance();

const VUE_APP_ACCESS_KEY = "AKIAQUIHCELPJ3PJXH55";
const VUE_APP_SECRET_ACCESS_KEY = "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";
const VUE_APP_AWS_REGION = "ap-northeast-2";
const VUE_APP_BUCKET_NAME = "s3ffmpeg";

aws.config.update({
  region: VUE_APP_AWS_REGION,
  secretAccessKey: VUE_APP_SECRET_ACCESS_KEY,
  accessKeyId: VUE_APP_ACCESS_KEY,
});

const s3 = new aws.S3({
  params: { Bucket: VUE_APP_BUCKET_NAME },
});

const fileUpload = (file, studioInfo, sceneId, user) => {
  s3.upload(
    {
      Key: `studio${studioInfo.studio_id}/${studioInfo.story_id}_${sceneId}.webm`,
      Body: file,
      ACL: "public-read",
    },
    async (err, data) => {
      if (err) {
        console.log(err);
        return null;
      }
      console.log("업로드 성공 ", data);
      await api
        .put(`/studio/recording`, {
          studio_id: studioInfo.studio_id,
          scene_id: sceneId,
          user_id: user.user_id,
          recording_video_url: data.Location,
        })
        .then((scs) => {
          console.log(scs);
        })
        .catch((error) => {
          console.log(error);
        });
      return data.Location;
    }
  );
};

const deleteObjectByKey = (key) => {
  const params = {
    Bucket: VUE_APP_BUCKET_NAME,
    Key: key,
  };
  const data = s3.deleteObject(params).promise();

  return data;
};

export { fileUpload, deleteObjectByKey };
