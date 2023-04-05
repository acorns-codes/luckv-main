<template>
  <section>
    <div>
      <div class="kindclass">
        <h3>{{ kind.name }}</h3>
        <p>{{ kind.content }}</p>
      </div>
      <VideoList :videoList="this.videoList" />
    </div>
  </section>
</template>

<script>
import VideoList from "@/views/videoPage/VideoList.vue";

export default {
  components: { VideoList },
  props: {
    kind: Object,
  },
  data() {
    return {
      videoList: "",
    };
  },

  created() {},
  mounted() {
    console.log(this.api);
    this.getVideo(this.kind.api);
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
    async getVideo(api) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/${api}`,
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
  height: 500px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 6% 0 0 0;
  & > div {
    width: 100%;
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    margin: 20% 0 0 0;
    & > div:nth-child(2) {
      width: 100%;
      display: flex;
      justify-content: center;
    }
  }
}

h3 {
  font-size: 62px;
  text-decoration: overline;
  text-decoration-color: #ff9414;
  text-decoration-thickness: 2px;
}

p {
  font-size: 30px;
}

.kindclass {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0% 0 0 10%;
}



</style>
