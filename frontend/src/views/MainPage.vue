<template>
  <FirstSection />
  <SecondSection />
  <ThirdSection />
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
import ThirdSection from "./MainPage/ThirdSection.vue";

// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,

    ThirdSection,
    // SignUpForm,
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
