import { apiInstance } from "./index";

const api = apiInstance();

async function getRecommendWork(success, fail) {
  await api.get(`/work/recommended-random`).then(success).catch(fail);
}

async function getWorkDetail(work, success, fail) {
  await api.get(`/work/detail`, work).then(success).catch(fail);
}

export { getRecommendWork, getWorkDetail };
