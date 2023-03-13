<template>
  <div class="main-container">
    <h1>마이페이지</h1>
    <div v-for="item in navList" :key="item">
      <router-link :to="{ path: item.value }">{{ item.name }}</router-link>
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
          value: "/sellerpage",
        },
        {
          name: "경매내역",
          value: "/sellerAuction",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.main-container {
  width: 1400px;
  height: 90px;
  display: flex;
  justify-content: space-between;
  align-items: end;
  margin-bottom: 10px;
  border-bottom: 1px solid black;
  h1 {
    width: 360px;
  }
  & > div {
    width: 150px;
    display: flex;

    & > a {
      display: flex;
      justify-content: space-around;
      font-size: 1.5rem;
      text-decoration: none;
      color: #343434;
    }
    & > a.router-link-exact-active {
      font-weight: bold;
    }
  }
}
</style>
