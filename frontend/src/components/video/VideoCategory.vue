<template>
  <div id="route-root">
    <template v-for="item in category" :key="item">
      <router-link
        :to="{
          name: this.type,
          params: { type: item.type },
          query: { page: 1 },
        }"
        >{{ item.name }}</router-link
      >
    </template>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: ["category", "type"],
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
