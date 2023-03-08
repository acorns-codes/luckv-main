<template>
  <HeaderView @child="LoginFormOpen" />
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
import HeaderView from "./HeaderView.vue";
import NavBar from "./NavBar.vue";
import FirstSection from "./FirstSection.vue";
import SecondSection from "./SecondSection.vue";
import LoginForm from "./LoginForm.vue";
import ThirdSection from "./ThirdSection.vue";
export default {
  name: "MainPage",
  components: {
    HeaderView,
    NavBar,
    FirstSection,
    SecondSection,
    LoginForm,
    ThirdSection,
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
