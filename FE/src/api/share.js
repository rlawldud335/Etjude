import { apiInstance } from "./index";

const api = apiInstance();

async function getFilmUpload(userId, success, fail) {
  console.log("# POST : 필름 업로드");
  await api.post(`/board/modal`, { user_id: userId }).then(success).catch(fail);
}
async function getFilmDetail(articleId, success, fail) {
  console.log("# GET : 필름 공유 상세 모달");
  await api.get(`/board/detail/${articleId}`).then(success).catch(fail);
}

export { getFilmUpload, getFilmDetail };
