import { apiInstance } from "./index";

const api = apiInstance();

async function getFilmUpload(userId, success, fail) {
  await api.post(`/board/modal`, { user_id: userId }).then(success).catch(fail);
}

async function getFilmDetail(articleId, success, fail) {
  await api.get(`/board/detail/${articleId}`).then(success).catch(fail);
}

async function putFilmShare(content, success, fail) {
  await api.put(`/board/create`, content).then(success).catch(fail);
}

export { getFilmUpload, getFilmDetail, putFilmShare };
