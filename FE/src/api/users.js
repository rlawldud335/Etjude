import { apiInstance } from "./index";

const api = apiInstance();

async function getMyPage(user, success, fail) {
  console.log("# GET : 마이페이지 정보");
  await api.post(`/mypage`, user).then(success).catch(fail);
}

async function getMyLike(user, success, fail) {
  console.log("# GET : 마이페이지 내 좋아요");
  await api.post(`/mypage/mylikes`, user).then(success).catch(fail);
}

export { getMyPage, getMyLike };
