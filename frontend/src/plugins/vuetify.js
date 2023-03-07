import Vue from "vue";
import Vuetify from "vuetify/lib/framework";
import "@/styles/sass/variables.scss"; // style.scss를 한번만 불러오면 자동으로 css로 컴파일 해줌
import { en, ko } from "vuetify/es5/locale";

// Vuetify
Vue.use(Vuetify);
export default new Vuetify({
  lang: {
    locales: { en, ko },
    current: "ko",
  },
});
