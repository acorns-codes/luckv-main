import { createRouter, createWebHistory } from "vue-router";
import store from "@/store";
const routes = [
  // 메인페이지
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/MainPage"),
    // 메타 필드
    // $route.matched를 반복하여 라우터 레코드의 메타필드를 검사한다.
  },
  {
    path: "/kakaologin",
    name: "kakaologin",
    component: () => import("@/views/Login/KakaoLogin.vue"),
    // 메타 필드
    // $route.matched를 반복하여 라우터 레코드의 메타필드를 검사한다.
  },
  //  전체 목록
  {
    path: "/auction",
    name: "auction",
    redirect: {
      name: "auctionList",
      params: { type: "all" },
      query: { page: 1 },
    },
    component: () => import("@/views/AuctionPage"),
    children: [
      {
        path: "/action/list/:type",
        name: "auctionList",
        props: true,
        component: () => import("@/views/AuctionPage"),
      },
    ],
  },
  // 무료 동영상 페이지
  {
    path: "/freevideo",
    name: "freevideo",
    redirect: {
      name: "freevideoList",
      params: { type: "all" },
      query: { page: 1 },
    },
    component: () => import("@/views/FreeVideoPage"),
    children: [
      {
        path: "/freevideo/list/:type",
        name: "freevideoList",
        component: () => import("@/views/FreeVideoPage"),
      },
    ],
  },
  // 구독 페이지
  {
    path: "/subscription",
    name: "subscription",
    redirect: {
      name: "subscriptionList",
      params: { type: "all" },
      query: { page: 1 },
    },
    component: () => import("@/views/SubscriptionPage"),
    children: [
      {
        path: "/subscription/list/:type",
        name: "subscriptionList",
        component: () => import("@/views/SubscriptionPage"),
      },
    ],
  },
  //  **   CScenter  **  //
  {
    path: "/cscenter",
    name: "cscenter",
    redirect: {
      name: "notice",
      query: { page: 1 },
    },
    component: () => import("@/views/CscenterPage.vue"),

    children: [
      // !! 공지사항  !! //
      // 공지사항 목록
      {
        path: "/notice",
        name: "notice",
        component: () => import("@/views/Cscenter/NoticePage"),
      },
      // 공지사항 상세페이지
      {
        path: "/notice/detail/:no",
        name: "noticedetail",
        component: () => import("@/views/Cscenter/Notice/NoticeDetail.vue"),
      },
      // 공지사항 수정
      {
        path: "/notice/detail/:no/edit",
        name: "noticedetailEdit",
        component: () => import("@/views/Cscenter/Notice/NoticeDetailEdit.vue"),
      },
      // ! QnA ! //
      // QnA 목록
      {
        path: "/qna",
        name: "qna",
        component: () => import("@/views/Cscenter/QnApage.vue"),
      },
      // QnA 상세페이지
      {
        path: "/qna/detail/:no",
        name: "qnadetail",
        component: () => import("@/views/Cscenter/QnA/QnADetail.vue"),
      },
      // QnA 수정
      {
        path: "/qna/detail/:no/edit",
        name: "qnadetailEdit",
        component: () => import("@/views/Cscenter/QnA/QnAEdit.vue"),
      },
      // QnA 새로 등록
      {
        path: "/post/qna",
        name: "postqna",
        component: () => import("@/views/Cscenter/QnA/QnAPost.vue"),
      },
      // ! FAQ ! //
      // FAQ 목록
      {
        path: "/faq",
        name: "faq",
        component: () => import("@/views/Cscenter/FAQpage.vue"),
      },
      // FAQ 등록
      {
        path: "/postfaq",
        name: "postfaq",
        component: () => import("@/views/Cscenter/FAQ/FAQPost.vue"),
      },
      // FAQ 수정
      {
        path: "/editfaq/:fno",
        name: "editfaq",
        component: () => import("@/views/Cscenter/FAQ/FAQEdit.vue"),
      },
    ],
  },

  //  **   마이페이지 **  //
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("@/components/MypageNav.vue"),
    children: [
      // 마이페이지 - 회원정보수정
      {
        path: "/mypage/edit/:mno",
        name: "mypageEdit",
        component: () => import("@/views/MyPage/MyPageEdit.vue"),
      },
      // 마이페이지 - 경매 낙찰 내역
      {
        path: "/mypage/detailList/:page",
        name: "mydetailList",
        component: () => import("@/views/MyPage/AttendList/AttendList.vue"),
      },
      // 마이페이지 - 경매 낙찰 상세 내역
      {
        path: "/mypage/mydetailList/detail/:ano",
        name: "mydetailListdetail",
        component: () => import("@/views/MyPage/AttendList/AttendDetail.vue"),
      },
      // 마이페이지 - 구독 내역
      {
        path: "/mypage/mysubdetail/:mno",
        name: "mysubdetail",
        component: () => import("@/views/MyPage/Subscriptrion/SubDetail.vue"),
      },
      // 마이페이지(셀러) - 동영상 목록
      {
        path: "/mypage/sellervideo",
        name: "sellervideo",
        component: () => import("@/views/SellerPage/VideoList/VideoList.vue"),

        children: [
          // 동영상 목록 - 경매
          {
            path: "/mypage/auction",
            name: "경매",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
          {
            path: "/mypage/free",
            name: "무료",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
          {
            path: "/mypage/subscription",
            name: "구독",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
        ],
      },
      // 마이페이지(셀러) - 경매 상세페이지
      {
        path: "/mypage/videodetail/:ano",
        name: "videoDetail",
        component: () =>
          import("@/views/SellerPage/VideoList/VideoDetail/VideoDetail.vue"),
        meta: { auth: true },
      },
      // 마이페이지(셀러) - 동영상 등록
      {
        path: "/mypage/postauction",
        name: "postauction",
        component: () => import("@/views/SellerPage/VideoPost/VideoPost.vue"),
      },
      // 마이페이지(셀러) - 동영상 수정
      {
        path: "/mypage/editauction/:ano",
        name: "editauction",
        component: () => import("@/views/SellerPage/VideoList/VideoEdit.vue"),
        meta: { auth: true },
      },
    ],
  },
  // 회원가입 페이지
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

// router.beforeEach((to, from, next) => {
//   if (to.meta.auth) {
//     console.log("인증이 필요합니다");
//   }
//   next();
// });

router.beforeEach((to, from, next) => {
  if (store.state.socket && store.state.socket.connected) {
    store.state.socket.disconnect(() => {
      console.log("WebSocket 연결이 종료되었습니다.");
      store.state.socket = "";
      next();
    });
  } else {
    next();
  }
});

export default router;
