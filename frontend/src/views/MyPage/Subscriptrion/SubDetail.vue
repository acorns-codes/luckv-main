<template>
  <div class="container">
    <h2>구독 내역</h2>
    <div>
      <div class="page-box"></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    justify-content: center;
    display: flex;
    padding-bottom: 10px;
  }
}
</style>
