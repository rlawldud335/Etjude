import { apiInstance } from "@/api/index";

const api = apiInstance();

async function getAllFilm(success, fail) {
  console.log("# GET : 전체 필름");
  await api({
    method: "get",
    url: "/board",
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

export { getAllFilm, getPopularFilm };
