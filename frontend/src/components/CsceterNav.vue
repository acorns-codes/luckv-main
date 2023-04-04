<template>
  <div id="root">
    <div id="cscenter-root">
      <div class="main-container">
        <h1>고객센터</h1>
        <div>
          <router-link
            :to="{
              name: 'notice',
              params: { page: 1 },
            }"
            >공지사항</router-link
          >
          <router-link
            :to="{
              name: 'qna',
              params: { page: 1 },
            }"
            >QnA</router-link
          >
          <router-link
            :to="{
              name: 'faq',
              params: { page: 1 },
            }"
            >FAQ</router-link
          >
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
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
#cscenter-root {
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
    margin-bottom: 10px;
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
