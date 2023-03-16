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
    component: () => import("@/views/Cscenter/NoticePage"),
  },
  {
    path: "/csqna",
    name: "csqna",
    component: () => import("@/views/Cscenter/QnApage.vue"),
  },
  {
    path: "/csfaq",
    name: "csfaq",
    component: () => import("@/views/Cscenter/FAQpage.vue"),
  },
  {
    path: "/mypage/:mno",
    name: "mypage",
    component: () => import("@/views/MyPage/MyPageEdit.vue"),
  },
  {
    path: "/mydetailList",
    name: "/mydetailList",
    component: () => import("@/views/MyPage/DetailList.vue"),
  },
  {
    path: "/sellerpage/:mno",
    name: "sellerpage",
    component: () => import("@/views/SellerPage/SellerPageEdit.vue"),
  },
  {
    path: "/sellerauction",
    name: "/sellerauction",
    component: () => import("@/views/SellerPage/AuctionList.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/Login/SignUpPage"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
