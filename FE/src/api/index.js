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
  // AxiosInst.interceptors.request.use((config) => {
  //   // config.headers.authorization = "token";
  //   config.headers["Access-Control-Allow-Origin"] = "*"; // CORS 설정(모든 리소스 허용)
  //   return config;
  // });

  return AxiosInst;
}

async function testMakeFilm(success, fail) {
  await axios
    .get("http://i8a605.p.ssafy.io:5000/api/v2/concateVideo", {
      studio_id: "studio1",
      film_id: "studio1",
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
