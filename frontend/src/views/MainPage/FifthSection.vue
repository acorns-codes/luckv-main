<template>
  <section>
    <div>
      <div>
        <h3>Last Auction</h3>
        <p>마감시간이 임박한 경매</p>
      </div>
      <div>
        <div v-for="item in videoData" :key="item" class="video-box">
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
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      videoData: "",
    };
  },
  mounted() {
    this.getVideo();
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    // 마우스오버시, 영상재생
    playVideo(e) {
      console.log(e);
      e.target.play();
      e.tartget.style.backgroundColor = "black";
      // this.view = !this.view;
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      console.log("마우스리브");
      e.target.pause();
      e.target.currentTime = 0;
      // this.view = !this.view;
    },
    // 동영상 불러오기
    async getVideo() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDeadline`,
        });

        this.videoData = res.data.data;
        // console.log(this.videoData);
      } catch (e) {
        console.log(e);
      }
    },
    // 상세페이지로 이동
    gotodetail(ano) {
      console.log("마우스 클릭");
      // const target = e.tartget;
      console.log(ano);
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
section {
  width: 100%;
  height: 350px;
  display: flex;
  justify-content: center;
  align-items: center;
  & > div {
    width: 1240px;
    display: flex;
    flex-direction: column;
    align-items: center;
    & > div:nth-child(2) {
      width: 1240px;
      display: flex;
      justify-content: center;
      /* align-items: center; */
    }
  }
}

.video-box {
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
p {
  font-size: 0.9rem;
}
</style>
