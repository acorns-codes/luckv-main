<template>
  <div>
    <ul>
      <li>linkV</li>
      <li>로그인</li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
div {
  color: $col;
  & > ul {
    /* @include display(space-between,center); */
    list-style-type: none;
    height: 100px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 50px 0 50px;
  }
}
</style>
