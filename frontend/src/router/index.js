import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/MainPage"),

    // 메타 필드
    // $route.matched를 반복하여 라우터 레코드의 메타필드를 검사한다.
  },
  // 비디오 전체 목록
  {
    path: "/video/all",
    name: "all",
    component: () => import("@/views/VideoPage"),
  },
  // 비디오 - 동물
  {
    path: "/video/animal",
    name: "animal",
    component: () => import("@/views/videoPage/VideoAnimal.vue"),
  },
  // 비디오 - 인물
  {
    path: "/video/character",
    name: "character",
    component: () => import("@/views/videoPage/VideoCharacter.vue"),
  }, // 비디오 - 건물
  {
    path: "/video/building",
    name: "building",
    component: () => import("@/views/videoPage/VideoBuilding.vue"),
  }, // 비디오 - 식물
  {
    path: "/video/plant",
    name: "plant",
    component: () => import("@/views/videoPage/VideoPlant.vue"),
  },
  // 비디오 - 기타
  {
    path: "/video/etc",
    name: "etc",
    component: () => import("@/views/videoPage/VideoEtc.vue"),
  },
  // 무료 동영상 페이지
  {
    path: "/freevideo",
    name: "freevideo",
    component: () => import("@/views/FreeVideoPage"),
  },
  // 구독 페이지
  {
    path: "/subscription",
    name: "subscription",
    component: () => import("@/views/SubscriptionPage"),
  },
  // 공지사항 목록
  {
    path: "/cscenter/:page",
    name: "cscenter",
    component: () => import("@/views/Cscenter/NoticePage"),
    meta: { auth: true },
  },
  // 공지사항 상세페이지
  {
    path: "/content/:no",
    name: "content",
    component: () => import("@/views/Cscenter/Notice/NoticeContent.vue"),
  },
  // 공지사항 수정
  {
    path: "/content/:no/edit",
    name: "contentEdit",
    component: () => import("@/views/Cscenter/Notice/NoticeEdit.vue"),
  },

  // QnA 상세페이지
  {
    path: "/csqnadetail/:no",
    name: "csqnadetail",
    component: () => import("@/views/Cscenter/QnA/QnADetail.vue"),
  },
  // QnA 수정
  {
    path: "/csqnadetail/:no/edit",
    name: "csqnadetailEdit",
    component: () => import("@/views/Cscenter/QnA/QnAEdit.vue"),
  },
  // QnA 목록
  {
    path: "/csqna/:page",
    name: "csqna",
    component: () => import("@/views/Cscenter/QnApage.vue"),
  },
  // QnA 새로 등록
  {
    path: "/postqna",
    name: "postqna",
    component: () => import("@/views/Cscenter/QnA/QnAPost.vue"),
  },

  // FAQ 목록
  {
    path: "/csfaq",
    name: "csfaq",
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
  // 마이페이지
  {
    path: "/mypage/:mno",
    name: "mypage",
    component: () => import("@/views/MyPage/MyPageEdit.vue"),
    meta: { auth: true },
  },
  // 마이페이지 - 경매 참여 내역
  {
    path: "/mydetailList",
    name: "/mydetailList",

    component: () => import("@/views/MyPage/DetailList.vue"),
  },
  // 마이페이지(셀러) - 경매 내역
  {
    path: "/sellerauction",
    name: "/sellerauction",
    component: () => import("@/views/SellerPage/AuctionList.vue"),
    meta: { auth: true },
  },
  // 마이페이지(셀러) - 경매 등록
  {
    path: "/postauction",
    name: "/postauction",
    component: () => import("@/views/SellerPage/Auction/AuctionPost.vue"),
    meta: { auth: true },
  },
  // 마이페이지(셀러) - 경매 상세페이지
  {
    path: "/auctiondetail/:ano",
    name: "auctionDetail",
    component: () => import("@/views/SellerPage/Auction/AuctionDetail.vue"),
    meta: { auth: true },
  },
  // 마이페이지(셀러) - 경매 수정
  {
    path: "/editauction/:ano",
    name: "editauction",
    component: () => import("@/views/SellerPage/Auction/AuctionEdit.vue"),
    meta: { auth: true },
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
export default router;
