import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendTopbar(success, fail) {
  console.log("# POST : 스튜디오 상단 바");
  await api.post(`/board/detail/{{articleId}}`).then(success).catch(fail);
}
async function getRecommendFilmDetail(success, fail) {
  console.log("# POST : 스튜디오 생성");
  await api.post(`/board/detail/`, { articleId: 1 }).then(success).catch(fail);
}

export { getRecommendTopbar, getRecommendFilmDetail };
