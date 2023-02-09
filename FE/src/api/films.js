import { apiInstance } from "@/api/index";

const api = apiInstance();

async function getFilmPage(pageNum, success, fail) {
  console.log("# GET : 전체 필름");
  await api({
    method: "get",
    url: "/board",
    params: {
      pageNum,
    },
  })
    .then(success)
    .catch(fail);
}

async function getPopularFilm(success, fail) {
  console.log("# GET : 추천 필름");
  await api({
    method: "get",
    url: "board/popular",
  })
    .then(success)
    .catch(fail);
}

export { getFilmPage, getPopularFilm };
