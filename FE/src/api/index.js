/* eslint-disable import/no-extraneous-dependencies */
import axios from "axios";

const URL = "https://withs.r-e.kr/api/v1";

// application/json 타입
function apiInstance() {
  const AxiosInst = axios.create({
    baseURL: URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  return AxiosInst;
}

async function testMakeFilm(success, fail) {
  await axios({
    method: "GET",
    url: "https://withs.r-e.kr/api/v2/concateVideo",
    params: {
      studio_id: "studio1",
      film_id: "studio2",
    },
  })
    .then(success)
    .catch(fail);
}

// multipart/form-data 형식
function apiFormInstance() {
  return axios.create({
    baseURL: URL,
    headers: {
      "Content-type": "multipart/form-data",
    },
  });
}

export { apiInstance, apiFormInstance, testMakeFilm };
