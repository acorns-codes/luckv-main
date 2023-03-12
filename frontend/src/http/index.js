import axios from "axios";
import store from "../store";

const http = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: { "content-type": "application/json" },
});

http.interceptors.request.use(
  (config) => {
    const isAuthenticated = store.getters["isAuthenticated"];
    if (isAuthenticated) {
      config.headers.common["Authorization"] = store.getters["getAccessToken"];
    }
    return config;
  },
  (error) => {
    // Do something with request error
    Promise.reject(error);
  }
);
http.defaults.headers.post["Content-Type"] =
  "application/x-www-form-urlencoded";

export default http;

// 로그인이 되어 있을 경우 모든 요청에 headers에 Authorization 으로 accessToken을 담아서 보낸다.
