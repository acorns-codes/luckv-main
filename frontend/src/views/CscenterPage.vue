<template>
  <div id="root">
    <div id="cscenter-root">
      <CsceterNavVue />
    </div>
  </div>
</template>

<script>
import CsceterNavVue from "@/components/CsceterNav.vue";

export default {
  components: { CsceterNavVue },
  // watch: {
  //   $route(to, form) {
  //     if (to.path !== form.path)
  //       this.$router.push({
  //         name: "notice",
  //         params: { page: 1 },
  //       });
  //   },
  // },

  mounted() {
    console.log(this.$route.name);
    this.$router.push({
      name: "notice",
      params: { page: 1 },
    });
  },
};
</script>

<style lang="scss" scoped>
#root {
  padding-top: 100px;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
#cscenter-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}

.cs-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding-top: 110px;
}
.card-box {
  width: 600px;
  height: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  align-self: center;
  h2 {
    color: orange;
    font-weight: bold;
    font-size: 2rem;
    margin-bottom: 15px;
  }
  p {
    font-size: 0.9rem;
  }
  button {
    margin: 10px;
  }
}
</style>
