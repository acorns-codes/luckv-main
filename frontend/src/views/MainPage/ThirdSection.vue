<template>
  <section>
    <div>
      <h3>다양한 분야의 동영상을 만나보세요</h3>
      <p>
        개성이 가득한 다양한 분야의 동영상을 먼저 선점할 수 있는 기회를
        노려보세요!
      </p>

      <div class="img-box">
        <button
          v-for="(item, index) in data"
          :key="index"
          :class="[class1, `item${index + 1}`]"
          @click="pushCategory(item.name)"
        >
          <div @mouseenter="MouseEvent(index)">
            <p>{{ item.category }}</p>
            <img
              class="img"
              :src="item.src"
              :class="mouseOver && index == this.boxIndex ? 'img-index' : ''"
            />
          </div>
        </button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      class1: "item",
      calss2: "item",
      boxIndex: "",
      mouseOver: false,
      thumbnail: [
        "https://img.freepik.com/free-photo/beautiful-colorful-firework-display-night-celebrate_74190-9527.jpg?t=st=1678242809~exp=1678243409~hmac=2b4178df739b6e483ecf73c745d8a62a1f793e994cebfbc5450283c2fd2dbb7d",
        "https://img.freepik.com/free-photo/one-hiking-lifestyle-summer-yoga_1150-1002.jpg?t=st=1678242809~exp=1678243409~hmac=3060c1e1d257abdba77c6ab11934379af5f7f37752771f32479f4f11ae19e00a",
      ],
      data: [
        {
          category: "동물",
          name: "animal",
          src: "https://img.freepik.com/free-photo/group-portrait-adorable-puppies_53876-64778.jpg?w=1380&t=st=1679632343~exp=1679632943~hmac=451eeb69879e78651191baf36abca4ee0443190526d4db9c8936e6a2816be470",
        },
        {
          category: "인물",
          name: "character",
          src: "https://img.freepik.com/premium-psd/young-adult-wearing-forest-apparel-tshirt_23-2149292866.jpg?w=1380",
        },
        {
          category: "건물",
          name: "building",
          src: "https://img.freepik.com/free-photo/day-city-view_1417-1777.jpg?w=1380&t=st=1679632412~exp=1679633012~hmac=5bdb6d7d4370875b4d4c0fb960741e3d457538d1446c2579fee389d9b724ab1c",
        },
        {
          category: "식물",
          name: "plant",
          src: "https://img.freepik.com/free-photo/monstera-deliciosa-plant-pot_53876-133119.jpg?w=1380&t=st=1679632443~exp=1679633043~hmac=a9cc68cafe96c8de2010aefd36a34a10931006b547199142ed9d8d01c2877d2e",
        },
        {
          category: "기타",
          name: "etc",
          src: "https://img.freepik.com/free-photo/easter-decorative-eggs-arrangement_23-2150245751.jpg?w=1380&t=st=1679632465~exp=1679633065~hmac=7f4e64d8144e5648b43f098903be9de1fe9049bf03ad2e5e3facf128b7d9d16b",
        },
      ],
    };
  },
  methods: {
    MouseEvent(e) {
      this.mouseOver = !this.mouseOver;
      this.boxIndex = e;
    },
    pushCategory(category) {
      this.$router.push({
        path: `/video/${category}`,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
section {
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
  background-color: rgba(255, 255, 255, 0.446);

  img {
    width: 100%;
    height: inherit;
    object-fit: cover;
    cursor: pointer;
  }

  .img-index {
    z-index: -1;
  }

  p {
    position: absolute;
  }
  & div {
    width: 100%;
    height: inherit;
    display: flex;
    justify-content: center;
    align-items: center;
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
