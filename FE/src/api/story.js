import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendStory(success, fail) {
  console.log("# GET : 추천 스토리 리스트");
  await api.get(`/story/recommended/popular`).then(success).catch(fail);
}

async function getStoryDetail(story, success, fail) {
  console.log("# POST : 스토리 상세 정보");
  await api.get(`/story/detail`, story).then(success).catch(fail);
}

export { getRecommendStory, getStoryDetail };
