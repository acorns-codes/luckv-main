// import Vue from "vue";
// import Vuetify from "vuetify/lib";
import { createVuetify } from "vuetify";
import "@/styles/sass/variables.scss";
// style.scss를 한번만 불러오면 자동으로 css로 컴파일 해줌
import { en, ko } from "vuetify/es5/locale";
import { VDataTable } from "vuetify/labs/VDataTable";
import * as labs from "vuetify/labs/components";

export default createVuetify({
  lang: {
    locales: { en, ko },
    current: "ko",
  },
  components: {
    VDataTable,
    ...labs,
  },
  defaults: {
    VDataTable: {
      fixedHeader: true,
      noDataText: "Results not found",
    },
  },
  icons: {
    defaultSet: "mdi",
  },
});
