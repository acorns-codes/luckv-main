<template>
  <section>
    <div>
      <h3>다양한 분야의 동영상을 만나보세요</h3>
      <p>
        개성이 가득한 다양한 분야의 동영상을 먼저 선점할 수 있는 기회를
        노려보세요!
      </p>

      <div class="img-box">
        <span class="item item1">
          <img
            src="https://img.freepik.com/free- photo/beautiful-colorful-firework-display-night-celebrate_74190-9527.jpg?t=st=1678242809~exp=1678243409~hmac=2b4178df739b6e483ecf73c745d8a62a1f793e994cebfbc5450283c2fd2dbb7d"
          />
        </span>
        <span class="item item2"
          ><img
            src="https://img.freepik.com/free-photo/one-hiking-lifestyle-summer-yoga_1150-1002.jpg?t=st=1678242809~exp=1678243409~hmac=3060c1e1d257abdba77c6ab11934379af5f7f37752771f32479f4f11ae19e00a"
        /></span>
        <span class="item item3"
          ><img
            src="https://img.freepik.com/premium-psd/young-adult-wearing-forest-apparel-tshirt_23-2149292866.jpg?w=1380"
        /></span>
        <span class="item item4"
          ><img
            src="https://img.freepik.com/premium-photo/cloe-up-person-celebratin-st-patrick-s-day_23-2149265280.jpg?w=1380"
        /></span>
        <span class="item item5"
          ><img
            src="https://img.freepik.com/free-photo/deliverywoman-holding-cups-coffee-black-wall_114579-76586.jpg?w=1380&t=st=1678329549~exp=1678330149~hmac=126d5c054f6fd1e15aa82a853a938364a0f75f2438d9ad8c326e223724e3294f"
        /></span>
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
  & > div {
    width: 1240px;
  }
}
.img-box {
  place-items: center;
  display: grid;
  grid-template-columns: repeat(3, 300px);
  grid-template-rows: repeat(3, 250px);
  margin: 50px;
  justify-content: center;
}

.item {
  width: 100%;
  height: 100%;
  width: 100%;
  height: 100%;
  background-color: aquamarine;
  color: #fff;
  display: flex;
  /* justify-content: center;
  align-items: center; */
  img {
    width: 100%;
    object-fit: cover;
  }
}
.item1 {
  grid-column: 1 / 4;
}

.item2 {
  grid-row: 2 / 4;
}

.item3 {
  grid-column: 2 / span 2;
}
</style>
