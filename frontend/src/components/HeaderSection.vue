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
import { apiGetAuctionAll } from "@/api/video";
import VideoList from "@/components/video/VideoList.vue";

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
    this.getVideo(this.kind.reqModel);
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
    async getVideo(req) {
      try {
        const res = await apiGetAuctionAll(req);
        this.videoList = res.list;
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  /* height: 500px; */
  display: flex;
  justify-content: center;
  align-items: center;
  & > div {
    width: 80%;
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    & > div:nth-child(2) {
      width: 100%;
      display: flex;
      justify-content: center;
    }
  }
}

h3 {
  font-size: 3rem;
  text-decoration: overline;
  text-decoration-color: #ff9414;
  text-decoration-thickness: 2px;
}

p {
  font-size: 2rem;
}

.kindclass {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0% 5% 0 10%;
  margin-top: 50px;
}

@media (max-width: 767px) {
  section {
    height: unset;
    margin: 0;
    & > div {
      width: 100%;
      margin: 0;
    }
    h3 {
      font-size: 2.5rem;
    }
    p {
      font-size: 1.5rem;
    }
  }
}
</style>
