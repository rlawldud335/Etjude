import { apiInstance } from "@/api/index";

const api = apiInstance();

async function searchWork(keyword, categoryId, page, success, fail) {
  await api({
    method: "get",
    url: "/work/search",
    params: {
      keyword,
      category_id: categoryId,
      pageNum: page,
    },
  })
    .then(success)
    .catch(fail);
}

async function searchStory(keyword, categoryId, page, success, fail) {
  await api({
    method: "get",
    url: "/story/search",
    params: {
      keyword,
      category_id: categoryId,
      pageNum: page,
    },
  })
    .then(success)
    .catch(fail);
}

export { searchWork, searchStory };
