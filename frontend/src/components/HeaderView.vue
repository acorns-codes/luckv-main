<template>
  <LoginForm v-if="this.$store.state.isClicked" />
  <div id="root" :class="{ short: isHeaderSmall }">
    <div id="header" :class="{ short: isHeaderSmall }">
      <router-link to="/">
        <h1>
          Luck
          <span>V</span>
        </h1></router-link
      >
      <div v-if="!isUserLogin">
        <router-link to="/" @click="onClicked">Login</router-link>
        <router-link to="/signup">Signup</router-link>
      </div>
      <div v-else>
        <router-link
          :to="{
            name: 'mypageEdit',
            params: { mno: this.sessionData.mno },
          }"
          >Mypage</router-link
        >
        <a href="/" @click="logoutUser">Logout</a>
      </div>
    </div>
    <div id="nav" :class="{ short: isHeaderSmall }">
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
          name: 'cscenter',
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
      isHeaderSmall: false,
      sessionData: this.$store.state.sessionStorageData,
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
  },
  methods: {
    handleScroll() {
      const scrollPosition = window.pageYOffset;
      if (scrollPosition > 50) {
        this.isHeaderSmall = true;
      } else {
        this.isHeaderSmall = false;
      }
    },
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
  justify-content: space-between;
  align-items: center;
  padding: 35px 50px 0px 50px;
  transition: padding 0.5s ease-in-out;
  & a {
    text-decoration: none;
    color: #f9f9f9;
  }
  & > div {
    width: 170px;
    display: flex;
    justify-content: space-between;
  }
}

#header.short {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 50px 0px 50px;

  & a {
    text-decoration: none;
    color: #f9f9f9;
  }
  & > div {
    width: 170px;
    display: flex;
    justify-content: space-between;
  }
}

#nav {
  width: 700px;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: auto;
  transition: width 0.5s ease-in-out;

  & a {
    text-decoration: none;
    color: #bcddff;
  }
}
#nav.short {
  width: 40%;
  position: fixed;
  right: 0px;
  left: 0px;
  top: 0px;

  & a {
    text-decoration: none;
    color: #bcddff;
  }
}
/* #nav a.router-link-exact-active {
  color: #ffffff;
  font-weight: bold;
} */
#root {
  position: fixed;
  width: 100%;
  top: 0px;
  transition: background-color 0.5s ease-in-out;
  z-index: 1;
}
#root.short {
  position: fixed;
  width: 100%;
  top: 0px;
  background-color: cadetblue;
}

h1 span {
  position: relative;
  top: 10px;
  display: inline-block;
  animation: bounce 0.3s ease infinite alternate;
  font-size: 30px;
  color: #ff9100;
}

@keyframes bounce {
  100% {
    top: -3px;
  }
}

#nav a.router-link-active {
  color: #ffffff;
  font-weight: bold;
}
</style>
