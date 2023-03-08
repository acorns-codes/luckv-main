import { createApp } from "vue";
import App from "./App.vue";
// import axios from "axios";
import store from "./store";
// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import router from "./router";

const vuetify = createVuetify({
  components,
  directives,
});

const app = createApp(App);
app.use(router).use(vuetify).use(store);
// app.provied("$axios", axios);
app.mount("#app");
