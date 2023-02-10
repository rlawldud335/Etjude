import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendTopbar(success, fail) {
  console.log("# POST : 스튜디오");
  await api.post(`/board/detail/{{articleId}}`).then(success).catch(fail);
}
async function getFilmDetail(articleId, success, fail) {
  console.log("# GET : 필름 공유 상세 모달");
  await api.get(`/board/detail/${articleId}`).then(success).catch(fail);
}

export { getRecommendTopbar, getFilmDetail };
