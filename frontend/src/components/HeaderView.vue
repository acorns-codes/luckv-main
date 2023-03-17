<template>
  <LoginForm v-if="this.$store.state.isClicked" />
  <div id="root">
    <div id="header">
      <router-link to="/">linkV</router-link>
      <div v-if="!$store.getters.isLogin">
        <router-link to="/" @click="onClicked">로그인</router-link>
        <router-link to="/signup">회원가입</router-link>
      </div>
      <div v-else>
        <router-link
          :to="{
            name: 'mypage',
            params: { mno: this.sessionData.mno },
          }"
          >마이페이지</router-link
        >

        <a href="/" @click="logoutUser">로그아웃</a>
      </div>
    </div>
    <div id="nav">
      <router-link to="/">홈</router-link>
      <router-link to="/video">동영상</router-link>
      <router-link to="/freevideo">무료</router-link>
      <router-link to="/subscription">구독</router-link>
      <router-link to="/cscenter/:page">고객센터</router-link>
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
      window.location.reload();
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
