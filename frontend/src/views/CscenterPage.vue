<template>
  <div id="root">
    <div id="cscenter-root">
      <CsceterNavVue />
    </div>

    <v-sheet
      v-if="this.$route.name === 'cscenter'"
      class="card-box"
      elevation="4"
    >
      <div>
        <h2>CScenter</h2>
        <p>언제나 LuckV를 이용해주셔서 감샇반디ㅏ</p>
        <p>이용중 불편사항이 있으시면 언제든 말씀해주세요!</p>
      </div>
    </v-sheet>
  </div>
</template>

<script>
import CsceterNavVue from "@/components/CsceterNav.vue";

export default {
  components: { CsceterNavVue },
  mounted() {
    console.log(this.$route.name);
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
  flex-direction: column;
}
#cscenter-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
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
