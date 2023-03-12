<template>
  <div class="main-container">
    <div>
      <h3>회원정보수정</h3>
      <SignUpForm />
    </div>
  </div>
</template>

<script>
import SignUpForm from "@/views/Login/SignUpForm.vue";
export default {
  components: { SignUpForm },
};
</script>

<style lang="scss" scoped>
.main-container {
  width: 100%;
  background-color: aquamarine;
  display: flex;
  flex-direction: column;
  align-items: center;
  & > div {
    width: 1440px;
    background-color: bisque;
  }
}
</style>
