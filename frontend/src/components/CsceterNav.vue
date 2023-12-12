<template>
  <div class="main-container">
    <h1>고객센터</h1>
    <div>
      <router-link
        :class="{ active: type.indexOf('notice') > -1 }"
        :to="{
          name: 'notice',
          query: { page: 1 },
        }"
        >공지사항</router-link
      >
      <router-link
        :class="{ active: type.indexOf('qna') > -1 }"
        :to="{
          name: 'qna',
          query: { page: 1 },
        }"
        >QnA</router-link
      >
      <router-link
        :class="{ active: type.indexOf('faq') > -1 }"
        :to="{
          name: 'faq',
          query: { page: 1 },
        }"
        >FAQ</router-link
      >
    </div>
  </div>
  <router-view />
</template>

<script>
export default {
  data() {
    return {};
  },
  computed: {
    type() {
      return this.$route.name;
    },
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
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 10px;
  }
  & > div > a.active {
    font-weight: bold;
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
}
</style>
