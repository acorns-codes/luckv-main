<template>
  <FirstSection />
  <SecondSection />
  <!-- <ThirdSection /> -->
  <FourthSection />
  <FifthSection />
  <HeaderSection
    name="FREE"
    content="무료로 제공되는 동영상"
    api="auctionFree"
  />
  <!-- <HeaderSection :kind="kind[1]" /> -->
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
// import ThirdSection from "./MainPage/ThirdSection.vue";
import FourthSection from "./MainPage/FourthSection.vue";
import FifthSection from "./MainPage/FifthSection.vue";
import HeaderSection from "@/components/HeaderSection.vue";

// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,
    // ThirdSection,
    FourthSection,
    FifthSection,
    HeaderSection,
  },
  data() {
    return {
      kind: [
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
