import axios from "axios";

const http = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

import { errorHandler } from "@/utils/errorHandler";
// 요청 인터셉터 추가
http.interceptors.request.use(
  (config) => {
    const headerConfig = {
      "Access-Control-Allow-Origin": "*",
      Accept: "*/*",
    };
    config.headers = Object.assign(config.headers, headerConfig);

    return config;
  },
  (error) => {
    // Do something with request error
    Promise.reject(error);
  }
);

// 응답 인터셉터 추가
http.interceptors.response.use(
  // 응답 데이터를 가공
  function (response) {
    if (response.data.res !== "OK" || response.data.msg !== "SUCCESS") {
      if (response.data.msg) {
        return errorHandler(response.data.msg);
      }
      return response;
    }
    return response;
  }
);

export default http;
