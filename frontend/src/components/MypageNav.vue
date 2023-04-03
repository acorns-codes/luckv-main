<template>
  <div id="root">
    <div id="mypage-root">
      <div class="main-container">
        <h1>마이페이지</h1>
        <div v-if="sessionData.auth === 'B'">
          <router-link
            :to="{ name: 'mypageEdit', params: { mno: sessionData.mno } }"
            >회원정보수정</router-link
          >
          <router-link
            :to="{
              name: 'mydetailList',
              params: { page: 1 },
            }"
            >구매내역</router-link
          >
        </div>
        <div v-else>
          <router-link
            :to="{ name: 'mypageEdit', params: { mno: sessionData.mno } }"
            >회원정보수정</router-link
          >
          <router-link
            :to="{
              name: 'sellerauction',
              params: { page: 1 },
            }"
            >동영상 목록</router-link
          >
          <router-link to="/postauction">동영상 등록</router-link>
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    console.log(this.$store.state.sessionStorageData.auth);
  },
  data() {
    return {
      sessionData: JSON.parse(sessionStorage.getItem("login")),
    };
  },
};
</script>

<style lang="scss" scoped>
#root {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
#mypage-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.main-container {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 40px;
  & > div {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  h1 {
    border-bottom: 1px solid #343434;
    margin-bottom: 15px;
  }
  a {
    font-size: 1.3rem;
    text-decoration: none;
    color: #343434;
    padding: 5px;
  }
  a.router-link-exact-active {
    font-weight: bold;
  }
}
</style>
