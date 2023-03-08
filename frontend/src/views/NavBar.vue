<template>
  <nav>
    <ul>
      <li>홈</li>
      <li>동영상</li>
      <li>무료</li>
      <li>구독</li>
      <li>고객센터</li>
    </ul>
  </nav>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
nav {
  height: 110px;
  display: flex;
  justify-content: center;
  & > ul {
    width: 700px;
    list-style-type: none;
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
}
</style>
