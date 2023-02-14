import { apiInstance } from "./index";

const api = apiInstance();

async function getMyPage(user, success, fail) {
  console.log("# POST : 마이페이지 정보");
  await api.post(`/mypage`, user).then(success).catch(fail);
}

async function getMyLike(user, success, fail) {
  console.log("# POST : 마이페이지 내 좋아요");
  await api.post(`/mypage/mylikes`, user).then(success).catch(fail);
}

async function getMyStudio(user, success, fail) {
  console.log("# POST : 마이페이지 내 스튜디오 ");
  await api.post(`/mypage/studio`, { user_id: user }).then(success).catch(fail);
}

async function getMyFilm(user, success, fail) {
  console.log("# POST : 마이페이지 내 필름 ");
  await api.post(`/mypage/film`, { user_id: user }).then(success).catch(fail);
}

export { getMyPage, getMyLike, getMyStudio, getMyFilm };
