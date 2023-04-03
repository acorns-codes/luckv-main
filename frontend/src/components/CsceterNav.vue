<template>
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
          name: 'csqna',
          params: { page: 1 },
        }"
        >QnA</router-link
      >
      <router-link
        :to="{
          name: 'csfaq',
          params: { page: 1 },
        }"
        >FAQ</router-link
      >
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
