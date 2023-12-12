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
            >경매 낙찰 내역</router-link
          >
          <router-link
            :to="{
              name: 'mysubdetail',
              params: { mno: sessionData.mno },
            }"
            >구독 내역</router-link
          >
        </div>
        <div v-else>
          <router-link
            :to="{ name: 'mypageEdit', params: { mno: sessionData.mno } }"
            >회원정보수정</router-link
          >
          <div id="video-list">
            <button @click="listOn">동영상 목록</button>
            <div>
              <router-link
                :to="{
                  name: '경매',
                  query: { page: 1 },
                }"
                >경매</router-link
              >
              <router-link
                :to="{
                  name: '무료',
                  query: { page: 1 },
                }"
                >무료</router-link
              >
              <router-link
                :to="{
                  name: '구독',
                  query: { page: 1 },
                }"
                >구독</router-link
              >
            </div>
          </div>
          <router-link
            :to="{
              name: 'postauction',
            }"
            >동영상 등록</router-link
          >
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
      list: false,
    };
  },
  methods: {
    listOn() {
      this.$router.push({
        name: "경매",
        query: { page: 1 },
      });
    },
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

#video-list {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  & > button {
    font-size: 1.3rem;
    padding: 5px;
  }
  & > div > a {
    display: flex;
    flex-direction: column;
    font-size: 1.1rem;
  }
}
</style>
