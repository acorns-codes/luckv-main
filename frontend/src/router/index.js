import { createRouter, createWebHistory } from "vue-router";
import MainPage from "../views/MainPage.vue";
import LoginSuccess from "../views/LoginSuccess.vue";
const routes = [
  {
    path: "/",
    name: "home",
    component: MainPage,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: LoginSuccess,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
