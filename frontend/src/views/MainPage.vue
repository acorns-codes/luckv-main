<template>
  <HeaderView @child="LoginFormOpen" />
  <!-- <SignUpForm /> -->
  <LoginForm
    v-if="isClicked"
    :isClicked="isClicked"
    @onSubmit="LoginFormClose"
  />
  <NavBar />
  <FirstSection />
  <SecondSection />
  <ThirdSection />
</template>

<script>
import HeaderView from "@/components/HeaderView.vue";
import NavBar from "@/components/NavBar.vue";
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
import ThirdSection from "./MainPage/ThirdSection.vue";
import LoginForm from "./Login/LoginForm.vue";
// import SignUpForm from "./Login/SignUpForm.vue";
export default {
  name: "MainPage",
  components: {
    HeaderView,
    NavBar,
    FirstSection,
    SecondSection,
    LoginForm,
    ThirdSection,
    // SignUpForm,
  },
  data() {
    return {
      isClicked: false,
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
