<template>
  <FirstSection />
  <SecondSection />
  <!-- <ThirdSection /> -->
  <HeaderSection :kind="kind[0]" />
  <HeaderSection :kind="kind[1]" />
  <HeaderSection :kind="kind[2]" />
  <HeaderSection :kind="kind[3]" />
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
// import ThirdSection from "./MainPage/ThirdSection.vue";
import HeaderSection from "@/components/HeaderSection.vue";

// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,
    // ThirdSection,

    HeaderSection,
  },
  data() {
    return {
      kind: [
        {
          name: "NEW",
          content: "새로 등록된 경매",
          api: "auctionNew",
        },
        {
          name: "Last Auction",
          content: "마감시간이 임박한 경매",
          api: "auctionDeadline",
        },
        {
          name: "FREE",
          content: "무료로 제공되는 동영상",
          api: "auctionFree",
        },
        {
          name: "SUBSCRIPTION",
          content: "구독 회원들에게만 제공되는 동영상",
          api: "auctionSubs",
        },
      ],
    };
  },

  methods: {
    LoginFormOpen() {
      this.isClicked = !this.isClicked;
    },
    LoginFormClose() {
      this.isClicked = false;
    },
  },
};
</script>
