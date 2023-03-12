<template>
  <div class="main-container">
    <div>
      <h1>마이페이지</h1>
      <ul>
        <li>회원정보수정</li>
        <li>구매내역</li>
        <li>구독내역</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.main-container {
  width: 100%;
  height: 90px;
  display: flex;
  justify-content: center;
  & > div {
    width: 1400px;
    display: flex;
    align-items: end;
    margin-bottom: 10px;
    border-bottom: 1px solid black;
    h1 {
      width: 360px;
    }
    & > ul {
      width: 1040px;
      list-style: none;
      display: flex;
      justify-content: space-around;
      font-size: 1.5rem;
    }
  }
}
</style>
