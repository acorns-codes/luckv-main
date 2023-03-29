<template>
  <section>
    <div>
      <div>
        <h3>Last Auction</h3>
        <p>마감시간이 임박한 경매</p>
      </div>
      <VideoList :videoList="this.videoList" />
    </div>
  </section>
</template>

<script>
import VideoList from "@/views/videoPage/VideoList.vue";

export default {
  components: { VideoList },

  data() {
    return {
      videoList: "",
    };
  },
  mounted() {
    this.getVideo();
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    // 마우스오버시, 영상재생
    playVideo(e) {
      e.target.play();
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      e.target.pause();
      e.target.currentTime = 0;
    },
    // 동영상 불러오기
    async getVideo() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDeadline`,
        });

        this.videoList = res.data.data;
      } catch (e) {
        console.log(e);
      }
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
      width: 1440px;
      display: flex;
      justify-content: center;
    }
  }
}

p {
  font-size: 0.9rem;
}
</style>
