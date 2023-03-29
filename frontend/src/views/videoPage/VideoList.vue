<template>
  <h1>video list</h1>
  <div class="video-box">
    <div v-for="item in videoList" :key="item">
      <div></div>
      <video
        :src="`${videoSrc}/videoplay?ano=${item.ano}`"
        muted
        loop
        @mouseover="playVideo"
        @mouseleave="stopVideo"
        @click="gotodetail(item.ano)"
      ></video>
    </div>
  </div>
</template>

<script>
export default {
  props: ["videoList"],
  mounted() {
    console.log(this.videoList);
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    // 마우스오버시, 영상재생
    playVideo(e) {
      e.target.play();
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      console.log("마우스리브");
      e.target.pause();
      e.target.currentTime = 0;
    },
    // 상세페이지로 이동
    gotodetail(ano) {
      var nWidth = "1300";
      var nHeight = "800";
      var xPos = document.body.clientWidth / 2 - nWidth / 2;
      xPos += window.screenLeft; //듀얼 모니터
      var yPos = screen.availHeight / 2 - nHeight / 2;
      let url = `/videodetail/${ano}`;
      // window.open(url, 500, 300);
      window.open(
        url,
        "popOpen",
        "width=" +
          nWidth +
          ",height=" +
          nHeight +
          ", left=" +
          xPos +
          ", top=" +
          yPos +
          ", toolbars=no, resizable=no, scrollbars=no"
      );
    },
  },
};
</script>

<style lang="scss" scoped>
span {
  color: #ff5e5e;
  font-weight: bolder;
}

.video-box {
  width: 1240px;
  display: flex;
  flex-flow: wrap;
  flex-wrap: wrap;

  justify-content: center;

  & > div {
    width: 320px;
    height: 200px;
    margin: 10px;
    cursor: pointer;

    & > video {
      width: inherit;
      height: inherit;
      object-fit: cover;
      border-radius: 10px;
    }
  }
}

.test {
  background-color: rgba(255, 255, 255, 0.251);
  position: absolute;
  z-index: 1;
  width: inherit;
  height: inherit;
}
p {
  font-size: 0.9rem;
}
</style>
