<template>
  <div class="video-box">
    <div v-for="item in videoList" :key="item">
      <div></div>
      <video
        :src="`${videoSrc}/videoplay?ano=${item.ano}`"
        muted
        loop
        @mouseover="playVideo"
        @mouseleave="stopVideo"
        @click="modal = true && getInfo(item.ano)"
      ></video>
    </div>
    <v-dialog v-model="modal" width="auto">
      <v-card>
        <VideoDetail :videoData="videoData" ref="videoDetail" />
        <v-card-actions>
          <v-btn block @click="closeModal">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import VideoDetail from "@/components/VideoDetail.vue";
export default {
  props: ["videoList"],
  components: { VideoDetail },
  data() {
    return {
      videoData: "",
      modal: false,
    };
  },
  mounted() {
    console.log(this.videoList);
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    // 각 동영상 상세 정보 불러오기
    async getInfo(ano) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${ano}`,
        });
        this.videoData = res.data.data;
        console.log(this.videoData);
        this.dday = res.data.data.lastDay;
        console.log(this.dday);
      } catch (e) {
        console.log(e);
      }
    },

    // 마우스오버시, 영상재생
    playVideo(e) {
      e.target.play();
      console.log(e);
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      //   console.log("마우스리브");
      e.target.pause();
      e.target.currentTime = 0;
    },
    closeModal() {
      this.modal = false;
      this.$refs.videoDetail.closeSocket();
      this.$emit("video", "", this.$route.params.page - 1);
      console.log("emit확인");
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
  flex-wrap: wrap;
  & > div {
    width: 264px;
    height: 250px;
    margin: 1%;
    cursor: pointer;
    & > video {
      width: inherit;
      height: inherit;
      object-fit: cover;
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

/* @media (min-width: 2500px) {
  .video-box > div {
    width: 632px;
    height: 500px;
    margin: 1%;
  }
} */
</style>
