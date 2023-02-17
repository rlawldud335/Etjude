import { apiInstance } from "@/api/index";

const api = apiInstance();

async function searchWork(params, success, fail) {
  await api({
    method: "get",
    url: "/work/search",
    params,
  })
    .then(success)
    .catch(fail);
}

async function searchStory(params, success, fail) {
  await api({
    method: "get",
    url: "/story/search",
    params,
  })
    .then(success)
    .catch(fail);
}

export { searchWork, searchStory };
