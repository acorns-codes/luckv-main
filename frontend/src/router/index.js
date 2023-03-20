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
    path: "/cscenter/:page",
    name: "cscenter",
    component: () => import("@/views/Cscenter/NoticePage"),
    meta: { auth: true },
    // children: [
    //   {
    //     path: "//content:no",
    //     name: "content",
    //     component: () => import("@/views/Cscenter/Notice/NoticeContent.vue"),
    //   },
    // ],
  },
  {
    path: "/csqna/:page",
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
    meta: { auth: true },
  },
  {
    path: "/mydetailList",
    name: "/mydetailList",

    component: () => import("@/views/MyPage/DetailList.vue"),
  },
  {
    path: "/sellerauction",
    name: "/sellerauction",
    component: () => import("@/views/SellerPage/AuctionList.vue"),
    meta: { auth: true },
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/Login/SignUpPage"),
  },
  {
    path: "/content/:no",
    name: "content",
    component: () => import("@/views/Cscenter/Notice/NoticeContent.vue"),
  },
  {
    path: "/postnotice",
    name: "postnotice",
    component: () => import("@/views/Cscenter/Notice/NoticePost.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.auth) {
    console.log("인증이 필요합니다");
  }
  next();
});
export default router;
