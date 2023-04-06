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
  // 비디오 전체 목록
  {
    path: "/video/:page",
    name: "all",
    component: () => import("@/views/AuctionPage"),
    children: [
      {
        path: "/:page",
        name: "전체",
        component: () => import("@/views/AuctionPage"),
      },
      {
        path: "/:page/animal",
        name: "animal",
        component: () => import("@/views/AuctionPage"),
      },
      {
        path: "/character/:page",
        name: "character",
        component: () => import("@/views/AuctionPage"),
      },
      {
        path: "/building/:page",
        name: "building",
        component: () => import("@/views/AuctionPage"),
      },
      {
        path: "/plant/:page",
        name: "plant",
        component: () => import("@/views/AuctionPage"),
      },
      {
        path: "/etc/:page",
        name: "etc",
        component: () => import("@/views/AuctionPage"),
      },
    ],
  },
  // 무료 동영상 페이지
  {
    path: "/freevideo/:page",
    name: "freevideo",
    component: () => import("@/views/FreeVideoPage"),
  },
  // 구독 페이지
  {
    path: "/subscription/:page",
    name: "subscription",
    component: () => import("@/views/SubscriptionPage"),
    children: [
      {
        path: "/:page",
        name: "구독전체",
        component: () => import("@/views/SubscriptionPage"),
      },
      {
        path: "/animal/:page",
        name: "subanimal",
        component: () => import("@/views/SubscriptionPage"),
      },
      {
        path: "/character/:page",
        name: "subcharacter",
        component: () => import("@/views/SubscriptionPage"),
      },
      {
        path: "/building/:page",
        name: "subbuilding",
        component: () => import("@/views/SubscriptionPage"),
      },
      {
        path: "/plant/:page",
        name: "subplant",
        component: () => import("@/views/SubscriptionPage"),
      },
      {
        path: "/etc/:page",
        name: "subetc",
        component: () => import("@/views/SubscriptionPage"),
      },
    ],
  },
  //  **   CScenter  **  //
  {
    path: "/cscenter",
    name: "cscenter",
    component: () => import("@/components/CsceterNav.vue"),
    children: [
      // !! 공지사항  !! //
      // 공지사항 목록
      {
        path: "/notice/:page",
        name: "notice",
        component: () => import("@/views/Cscenter/NoticePage"),
      },
      // 공지사항 상세페이지
      {
        path: "/noticedetail/:no",
        name: "noticedetail",
        component: () => import("@/views/Cscenter/Notice/NoticeDetail.vue"),
      },
      // 공지사항 수정
      {
        path: "/noticedetail/:no/edit",
        name: "noticedetailEdit",
        component: () => import("@/views/Cscenter/Notice/NoticeDetailEdit.vue"),
      },
      // ! QnA ! //
      // QnA 목록
      {
        path: "/qna/:page",
        name: "qna",
        component: () => import("@/views/Cscenter/QnApage.vue"),
      },
      // QnA 상세페이지
      {
        path: "/qnadetail/:no",
        name: "qnadetail",
        component: () => import("@/views/Cscenter/QnA/QnADetail.vue"),
      },
      // QnA 수정
      {
        path: "/qnadetail/:no/edit",
        name: "qnadetailEdit",
        component: () => import("@/views/Cscenter/QnA/QnAEdit.vue"),
      },
      // QnA 새로 등록
      {
        path: "/postqna",
        name: "postqna",
        component: () => import("@/views/Cscenter/QnA/QnAPost.vue"),
      },
      // ! FAQ ! //
      // FAQ 목록
      {
        path: "/faq/:page",
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

  //  **   CScenter  **  //
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("@/components/MypageNav.vue"),
    children: [
      // 마이페이지 - 회원정보수정
      {
        path: "/edit/:mno",
        name: "mypageEdit",
        component: () => import("@/views/MyPage/MyPageEdit.vue"),
      },
      // 마이페이지 - 경매 낙찰 내역
      {
        path: "/mydetailList/:page",
        name: "mydetailList",
        component: () => import("@/views/MyPage/AttendList/AttendList.vue"),
      },
      // 마이페이지 - 경매 낙찰 상세 내역
      {
        path: "/mydetailList/detail/:ano",
        name: "mydetailListdetail",
        component: () => import("@/views/MyPage/AttendList/AttendDetail.vue"),
      },
      // 마이페이지 - 구독 내역
      {
        path: "/mysubdetail/:mno",
        name: "mysubdetail",
        component: () => import("@/views/MyPage/Subscriptrion/SubDetail.vue"),
      },
      // 마이페이지(셀러) - 동영상 목록
      {
        path: "/sellervideo",
        name: "sellervideo",
        component: () => import("@/views/SellerPage/VideoList/VideoList.vue"),

        children: [
          // 동영상 목록 - 경매
          {
            path: "/auction/:page",
            name: "경매",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
          {
            path: "/free/:page",
            name: "무료",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
          {
            path: "/subscription/:page",
            name: "구독",
            component: () =>
              import("@/views/SellerPage/VideoList/VideoList.vue"),
          },
        ],
      },
      // 마이페이지(셀러) - 경매 상세페이지
      {
        path: "/videodetail/:ano",
        name: "videoDetail",
        component: () =>
          import("@/views/SellerPage/VideoList/VideoDetail/VideoDetail.vue"),
        meta: { auth: true },
      },
      // 마이페이지(셀러) - 동영상 등록
      {
        path: "/postauction",
        name: "/postauction",
        component: () => import("@/views/SellerPage/VideoPost/VideoPost.vue"),
      },
      // 마이페이지(셀러) - 동영상 수정
      {
        path: "/editauction/:ano",
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
      console.log(store.state.socket);
      next();
    });
  } else {
    next();
  }
});

export default router;
