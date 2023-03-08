<template>
  <section>
    <div>
      <h3>다양한 분야의 동영상을 만나보세요</h3>
      <p>
        개성이 가득한 다양한 분야의 동영상을 먼저 선점할 수 있는 기회를
        노려보세요!
      </p>

      <div v-for="src in thumbnail" :key="src" class="img-box">
        <img :src="src" alt="썸네일" />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      thumbnail: [
        "https://img.freepik.com/free-photo/beautiful-colorful-firework-display-night-celebrate_74190-9527.jpg?t=st=1678242809~exp=1678243409~hmac=2b4178df739b6e483ecf73c745d8a62a1f793e994cebfbc5450283c2fd2dbb7d",
        "https://img.freepik.com/free-photo/one-hiking-lifestyle-summer-yoga_1150-1002.jpg?t=st=1678242809~exp=1678243409~hmac=3060c1e1d257abdba77c6ab11934379af5f7f37752771f32479f4f11ae19e00a",
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
secion {
  width: 100%;
  display: flex;
  justify-content: center;

  & > div {
    width: 1240px;
    display: flex;
  }
}
img-box {
  & > img {
    width: inherit/3;
    object-fit: cover;
  }
}
</style>
