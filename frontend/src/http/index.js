import axios from "axios";

const http = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

// 요청 인터셉터 추가
http.interceptors.request.use(
  (config) => {
    const headerConfig = {
      "Access-Control-Allow-Origin": "*",
      "Content-type": "application/json",
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
    console.log(response, "응답값 보여줘");
    // if (isApiUrl(response.config.url) && response.data.msg !== "SUCCESS") {
    if (response.data.statusCode !== 200) {
      if (response.data.responseMessage) {
        return alert(response.data.responseMessage);
      }
      return response;
    }
    return response;
  }
);

export default http;

// 로그인이 되어 있을 경우 모든 요청에 headers에 Authorization 으로 accessToken을 담아서 보낸다.
