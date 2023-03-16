<template>
  <div class="main-container">
    <div>
      <h1>마이페이지</h1>
      <div v-for="item in navList" :key="item">
        <router-link :to="{ path: item.value }">{{ item.name }}</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      navList: [
        {
          name: "회원정보수정",
          value: `/sellerpage/${this.$store.state.sessionStorageData.mno}`,
        },
        {
          name: "경매내역",
          value: "/sellerauction",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.main-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  & > div {
    width: 1400px;
    height: 90px;
    display: flex;
    align-items: end;
    justify-content: space-between;
    margin-bottom: 10px;
    border-bottom: 1px solid black;
    h1 {
      width: 360px;
    }
    & > div {
      width: 150px;

      & > a {
        font-size: 1.5rem;
        text-decoration: none;
        color: #343434;
      }
      & > a.router-link-exact-active {
        font-weight: bold;
      }
    }
  }
}
</style>
