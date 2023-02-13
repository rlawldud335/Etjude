/* eslint-disable no-undef */
/* eslint-disable import/no-extraneous-dependencies */
import aws from "aws-sdk";

const VUE_APP_ACCESS_KEY = "AKIAQUIHCELPJ3PJXH55";
const VUE_APP_SECRET_ACCESS_KEY = "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";
const VUE_APP_AWS_REGION = "ap-northeast-2";
const VUE_APP_BUCKET_NAME = "s3ffmpegtest";

aws.config.update({
  region: VUE_APP_AWS_REGION,
  secretAccessKey: VUE_APP_SECRET_ACCESS_KEY,
  accessKeyId: VUE_APP_ACCESS_KEY,
});

const s3 = new aws.S3({
  params: { Bucket: VUE_APP_BUCKET_NAME },
});

function fileUpload(file, studioInfo, sceneId, success, fail) {
  s3.upload(
    {
      Key: `studio${studioInfo.studio_id}/${studioInfo.story_id}_${sceneId}.webm`,
      Body: file,
      ACL: "public-read",
    },
    (err, data) => {
      if (err) {
        fail(err);
      } else {
        success(data);
      }
    }
  );
}

const deleteObjectByKey = (key) => {
  const params = {
    Bucket: VUE_APP_BUCKET_NAME,
    Key: key,
  };
  const data = s3.deleteObject(params).promise();

  return data;
};

export { fileUpload, deleteObjectByKey };
