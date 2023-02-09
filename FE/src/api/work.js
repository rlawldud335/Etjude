import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendWork(success, fail) {
  console.log("# GET : 추천 작품 리스트");
  await api.get(`/work/recommended-random`).then(success).catch(fail);
}

async function getWorkDetail(work, success, fail) {
  console.log("# GET : 작품 상세보기");
  await api.get(`/work/detail`, work).then(success).catch(fail);
}

export { getRecommendWork, getWorkDetail };
