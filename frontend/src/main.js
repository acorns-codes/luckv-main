import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
// import AppLayout from "./Layouts/AppLayout.vue";
import store from "./store";
import axios from "axios";
import VueCookies from "vue-cookies";
import "@mdi/font/css/materialdesignicons.css";
// icon이 안나와서 import 하니까 바로 나타남

import "@/styles/sass/main.scss";

// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    themes: {
      light: {
        primary: "#3f51b5",
        secondary: "#b0bec5",
        accent: "#8c9eff",
        error: "#b71c1c",
      },
    },
  },
});

// [앱 생성 실시 및 라우터 사용 설정 / store 사용 / main 아이디 : 렌더링 시작점
const app = createApp(App);
app.use(router).use(store).mount("#app");
// [라우터 사용 설정]
app.config.globalProperties.$axios = axios;
// [axios / http 통신 ]
app.use(vuetify);
// vuetify 사용 설정
// app.component("AppLayout", AppLayout);
// 레이아웃 사용
app.use(VueCookies);
// VueCookies 사용 설정
