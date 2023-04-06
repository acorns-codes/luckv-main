<template>
  <div class="cs-center">
    <v-sheet
      v-if="this.$route.name === 'cscenter'"
      class="card-box"
      elevation="4"
    >
      <div>
        <h2>CScenter</h2>
        <p>언제나 LuckV를 이용해주셔서 감사합니다.</p>
        <p>이용중 불편사항이 있으시면 언제든 말씀해주세요!</p>
      </div>
    </v-sheet>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.cs-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding-top: 110px;
}
.card-box {
  width: 600px;
  height: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  align-self: center;
  h2 {
    color: orange;
    font-weight: bold;
    font-size: 2rem;
    margin-bottom: 15px;
  }
  p {
    font-size: 0.9rem;
  }
  button {
    margin: 10px;
  }
}
</style>
