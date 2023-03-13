<template>
  <LoginForm v-if="this.$store.state.isClicked" />
  <div id="header">
    <ul>
      <li>linkV</li>
      <template v-if="!$store.getters.isLogin">
        <li @click="onClicked">로그인</li>
      </template>
      <template v-else>
        <li>마이페이지</li>
        <li @click="logoutUser">로그아웃</li>
      </template>
    </ul>
    <div id="nav">
      <router-link to="/">홈</router-link>
      <router-link to="/video">동영상</router-link>
      <router-link to="/freevideo">무료</router-link>
      <router-link to="/subscription">구독</router-link>
      <router-link to="/cscenter">고객센터</router-link>
      <router-link to="/mypage">마이페이지</router-link>
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
    return {};
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
    logoutUser() {
      return this.$store.commit("clearUserId");
    },
  },
  methods: {
    onClicked() {
      this.$store.state.isClicked = !this.$store.state.isClicked;
      console.log(this.$store.state.isClicked);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/styles/sass/variables.scss";
#header {
  color: $col-1;
  & > ul {
    list-style-type: none;
    height: 100px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 50px 0 50px;
    & > li {
      cursor: pointer;
    }
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
