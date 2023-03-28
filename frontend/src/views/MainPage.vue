<template>
  <FirstSection />
  <SecondSection />
  <ThirdSection />
  <FourthSection />
  <FifthSection />
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
import ThirdSection from "./MainPage/ThirdSection.vue";
import FourthSection from "./MainPage/FourthSection.vue";
import FifthSection from "./MainPage/FifthSection.vue";

// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,
    ThirdSection,
    FourthSection,
    FifthSection,
  },
  data() {
    return {};
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
