<template>
  <LoginForm v-if="this.$store.state.isClicked" />
  <div id="root">
    <div id="header">
      <router-link to="/">linkV</router-link>
      <div v-if="!isUserLogin">
        <router-link to="/" @click="onClicked">Login</router-link>
        <router-link to="/signup">Signup</router-link>
      </div>
      <div v-else>
        <router-link
          :to="{
            name: 'mypage',
            params: { mno: this.sessionData.mno },
          }"
          >Mypage</router-link
        >
        <a href="/" @click="logoutUser">Logout</a>
      </div>
    </div>
    <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link
        :to="{
          name: 'all',
          params: { page: 1 },
        }"
        >Auction</router-link
      >
      <router-link
        :to="{
          name: 'freevideo',
          params: { page: 1 },
        }"
        >Free</router-link
      >
      <router-link
        :to="{
          name: 'subscription',
          params: { page: 1 },
        }"
        >Subscription</router-link
      >
      <router-link
        :to="{
          name: 'notice',
          params: { page: 1 },
        }"
        >CScenter</router-link
      >
    </div>
  </div>
</template>

<script>
import LoginForm from "@/views/Login/LoginForm.vue";
export default {
  components: {
    LoginForm,
  },
  data() {
    return {
      sessionData: this.$store.state.sessionStorageData,
    };
  },

  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
  },
  methods: {
    onClicked() {
      this.$store.state.isClicked = !this.$store.state.isClicked;
      // console.log(this.$store.state.isClicked);
    },
    logoutUser() {
      console.log("로그아웃유저");
      this.$store.commit("clearUser");
      this.$store.state.sessionStorageData = "";
      this.$router.go();
    },
  },
  // created() {
  //   console.log(this.$store.state.sessionStorageData);
  //   console.log(this.userData);
  // },
};
</script>

<style lang="scss" scoped>
@import "@/styles/sass/variables.scss";
#header {
  display: flex;
  height: 100px;
  justify-content: space-between;
  align-items: center;
  padding: 0 50px 0 50px;

  & a {
    text-decoration: none;
    color: #2c3e50;
  }
  & > div {
    width: 170px;
    display: flex;
    justify-content: space-between;
  }
}

#nav {
  width: 700px;
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: auto;

  & a {
    text-decoration: none;
    color: #2c3e50;
  }
}
#nav a.router-link-exact-active {
  color: #ff9414;
  font-weight: bold;
}
</style>
