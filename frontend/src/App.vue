<template>
  <div id="app">
    <anmaition-bar />
    <app-layout-home>
      <router-view />
    </app-layout-home>
  </div>
</template>

<script>
import AnmaitionBar from "./views/MainPage/AnmaitionBar.vue";
import AppLayoutHome from "./Layouts/AppLayoutHome.vue";
export default {
  name: "App",
  components: { AnmaitionBar, AppLayoutHome },
  mounted() {
    this.sesstionCheck();
  },
  methods: {
    sesstionCheck() {
      let data = sessionStorage.getItem("login");
      if (!data) {
        this.$store.state.sessionStorageData = "";
      }
    },
  },
};
</script>

<style lang="scss">
#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
  text-align: center;
  color: #2c3e50;
  padding-bottom: 50px;
}
</style>
