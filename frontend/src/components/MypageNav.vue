<template>
  <div class="main-container">
    <h1>마이페이지</h1>
    <div v-if="sessionData.auth === 'B'">
      <router-link :to="`/mypage/${sessionData.mno}`">회원정보수정</router-link>
      <router-link to="/mydetailList">구매내역</router-link>
      <router-link to="/">구독내역</router-link>
    </div>
    <div v-else>
      <router-link :to="`/mypage/${sessionData.mno}`">회원정보수정</router-link>
      <router-link to="/sellerauction">동영상 목록</router-link>
      <router-link to="/postauction">동영상 등록</router-link>
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
