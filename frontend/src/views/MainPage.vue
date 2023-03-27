<template>
  <FirstSection />
  <SecondSection />
  <ThirdSection />
  <ForthSection />
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
import ThirdSection from "./MainPage/ThirdSection.vue";
import ForthSection from "./MainPage/ForthSection.vue";

// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,
    ThirdSection,
    ForthSection,
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
