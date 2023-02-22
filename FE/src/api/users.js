import { apiInstance } from "./index";

const api = apiInstance();

async function getMyPage(user, success, fail) {
  await api.post(`/mypage`, user).then(success).catch(fail);
}

async function getMyLike(user, success, fail) {
  await api.post(`/mypage/mylikes`, user).then(success).catch(fail);
}

async function getMyStudio(user, success, fail) {
  await api.post(`/mypage/studio`, user).then(success).catch(fail);
}

async function getMyFilm(user, success, fail) {
  await api.post(`/mypage/film`, user).then(success).catch(fail);
}

async function getMyArticle(user, success, fail) {
  await api.post(`/mypage/articles`, user).then(success).catch(fail);
}

async function getMyComment(user, success, fail) {
  await api.post(`/mypage/comments`, user).then(success).catch(fail);
}

async function getUserSearch(email, success, fail) {
  await api.post(`/studio/search`, email).then(success).catch(fail);
}

export { getMyPage, getMyLike, getMyStudio, getMyFilm, getMyArticle, getMyComment, getUserSearch };
