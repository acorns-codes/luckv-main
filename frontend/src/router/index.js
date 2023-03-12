import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/MainPage"),

    // 메타 필드
    // $route.matched를 반복하여 라우터 레코드의 메타필드를 검사한다.
  },
  {
    path: "/video",
    name: "video",
    component: () => import("@/views/VideoPage"),
  },
  {
    path: "/freevideo",
    name: "freevideo",
    component: () => import("@/views/FreeVideoPage"),
  },

  {
    path: "/subscription",
    name: "subscription",
    component: () => import("@/views/SubscriptionPage"),
  },
  {
    path: "/cscenter",
    name: "cscenter",
    component: () => import("@/views/CscenterPage"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("@/views/MyPage"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
