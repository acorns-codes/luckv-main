<!-- 메인페이지 첫번째 섹션 -->
<!-- 광고 배너 부분 -->
<template>
  <!-- <section>
    <div></div>
  </section> -->
  <section>
    <div class="btext" v-if="showBtext">
      <h1>저작권을 판매하고 수익을 얻으세요</h1>
      <p>
        나만의 동영상을 최고가로 판매하고, 다양한 사람들이 사용할 수 있도록
        구독서비스를 제공해보세요<br />
        저작권을 사고 판매하는 다양한 서비스를 만나보세요
      </p>
      <div v-if="!isUserLogin">
        <router-link to="/signup">
          <v-btn> 회원가입 바로가기 </v-btn>
        </router-link>
      </div>
    </div>
    <div class="btext2" v-else>
      <h1>다양한 분야의 동영상을 만나보세요</h1>
      <p>
        화면을 아래로 스크롤하거나 화살표 버튼을 클릭해서 다양한 분야의 동영상을
        미리 만나보세요<br />
      </p>
      <div @click="arrow">
        <img :src="require(`@/assets/images/down_arrow.svg`)" />
      </div>
    </div>
    <div>
      <video preload="auto" :src="src" muted autoplay loop></video>
    </div>
  </section>
  <!-- 
  controls 영상 컨트로 막대표시
  autoplay 영상 자동 재생
  loop 영상 반복 재생
  muted 영상 재생되지만 음소거
  preload 영상 재생 전에 미디어 파링릉 다운 할 것인지 정함
  poster 영상 재생할 수 없을 경우 표시하는 것
 -->
</template>

<script>
import src from "@/assets/video/70796.mp4";
export default {
  data() {
    return {
      src: src,
      showBtext: true,
    };
  },
  created() {
    setInterval(() => {
      this.showBtext = !this.showBtext;
    }, 5000);
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
  },
  methods: {
    arrow() {
      const section = document.getElementById("f-div");
      const offset = section.offsetTop - 40;
      window.scrollTo({
        top: offset,
        behavior: "smooth",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  height: 850px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  & > div:nth-child(1) {
    width: 1440px;
    height: 600px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    & > h1 {
      font-size: 3rem;
    }
  }
  & > div:nth-child(2) {
    width: 100%;
    height: 100%;
    top: 0px;
    position: absolute;
    z-index: -1;
  }
}
video {
  width: inherit;
  height: inherit;
  object-fit: cover;
}

.btext {
  animation: slideInFromTop 1s ease-out;
}
.btext2 {
  animation: slideInFromTop 1s ease-out;
  display: none;
}

@media (min-width: 2500px) {
  .btext {
    margin-block-start: 10%;
  }

  .btext2 {
    margin-block-start: 10%;
  }
}

button {
  width: 200px;
  margin: 15px;
  background-color: #5f9ea0;
}
a {
  color: white;

  text-decoration: none;
}

@keyframes slideInFromTop {
  0% {
    transform: translateY(100%);
  }
  100% {
    transform: translateY(0);
  }
}
</style>
