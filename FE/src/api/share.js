import { apiInstance } from "./index";

const api = apiInstance();

async function getFilmUpload(userId, success, fail) {
  console.log("# POST : 나의 필름 데이터 가져오기");
  await api.post(`/board/modal`, { user_id: userId }).then(success).catch(fail);
}

async function getFilmDetail(articleId, success, fail) {
  console.log("# GET : 필름 공유글 보기");
  await api.get(`/board/detail/${articleId}`).then(success).catch(fail);
}

async function putFilmShare(content, success, fail) {
  console.log("# GET : 필름 공유 게시글 업로드");
  await api.put(`/board/create`, content).then(success).catch(fail);
}

export { getFilmUpload, getFilmDetail, putFilmShare };
