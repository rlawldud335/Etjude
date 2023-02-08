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

// multipart/form-data 형식
function apiFormInstance() {
  return axios.create({
    baseURL: URL,
    headers: {
      "Content-type": "multipart/form-data",
    },
  });
}

export { apiInstance, apiFormInstance };
