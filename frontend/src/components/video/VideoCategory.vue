<template>
  <div id="route-root">
    <router-link
      :to="{
        name: category[0],
        params: { page: 1 },
      }"
      >ALL</router-link
    >
    <router-link
      :to="{
        name: category[1],
        params: { page: 1 },
      }"
      >동물</router-link
    >
    <router-link
      :to="{
        name: category[2],
        params: { page: 1 },
      }"
      >인물</router-link
    >
    <router-link
      :to="{
        name: category[3],
        params: { page: 1 },
      }"
      >건물</router-link
    >
    <router-link
      :to="{
        name: category[4],
        params: { page: 1 },
      }"
      >식물</router-link
    >
    <router-link
      :to="{
        name: category[5],
        params: { page: 1 },
      }"
      >기타</router-link
    >
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: ["category"],

  methods: {
    clickCategory(category) {
      //   console.log(category);
      this.$router.push({
        name: category,
      });
      //   console.log(this.$route.name);
    },
  },
};
</script>

<style lang="scss" scoped>
#route-root {
  width: 1440px;
  height: 50px;
  display: flex;
  /* background-color: ; */
  align-items: flex-end;
  justify-content: center;
  margin: 10px;

  & a {
    width: 100px;
    text-decoration: none;
    color: #68dac1;
    padding-bottom: 10px;
    font-family: "KCC-Ganpan";
  }
  & a.router-link-active {
    color: #00aaf8;
    border-bottom: 3px solid #00aaf8;
    padding-bottom: 7px;
  }
}
</style>
