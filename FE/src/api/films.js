import { apiInstance } from "@/api/index";

const api = apiInstance();

async function getFilmPage(pageNum, success, fail) {
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
  await api({
    method: "get",
    url: "/board/popular",
  })
    .then(success)
    .catch(fail);
}

async function saveMakedFilm(studioId, filmUrl, success, fail) {
  await api
    .post("/studio/film/create", {
      studio_id: studioId,
      film_video_url: filmUrl,
    })
    .then(success)
    .catch(fail);
}

export { getFilmPage, getPopularFilm, saveMakedFilm };
