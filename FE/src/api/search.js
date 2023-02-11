import { apiInstance } from "@/api/index";

const api = apiInstance();

async function searchWork(keyword, categoryId, success, fail) {
  await api({
    method: "get",
    url: "/work/search",
    params: {
      keyword,
      category_id: categoryId,
    },
  })
    .then(success)
    .catch(fail);
}

async function searchStory(keyword, categoryId, success, fail) {
  await api({
    method: "get",
    url: "/story/search",
    params: {
      keyword,
      category_id: categoryId,
    },
  })
    .then(success)
    .catch(fail);
}

export { searchWork, searchStory };
