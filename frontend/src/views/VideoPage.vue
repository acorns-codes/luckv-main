<template>
  <div id="root">
    <div id="info-box">
      <h2 class="test_obj hover-event">Auction Video</h2>
    </div>
    <div id="page-root">
      <VideoCategory :category="category" />
      <VideoList :videoList="this.videoList" @video="video" />
      <div class="page-box">
        <button @click="movetopreviouspage">
          <v-icon> mdi-chevron-left </v-icon>
        </button>
        <div>{{ this.$route.params.page }} / {{ totalpage }}</div>
        <button @click="movetonextpage">
          <!-- 다음페이지로 이동 -->
          <v-icon> mdi-chevron-right </v-icon>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import VideoList from "@/views/videoPage/VideoList.vue";
import VideoCategory from "@/components/VideoCategory.vue";
export default {
  components: { VideoCategory, VideoList },
  data() {
    return {
      videoList: "",
      cnt: "",
      defaultCnt: 10,
      page: "",
      category: ["전체", "animal", "character", "building", "plant", "etc"],
    };
  },
  computed: {
    // 총 페이지 수 계산
    totalpage() {
      if (this.cnt == 0) {
        // 현재 게시판 글 갯수가 0개일때 총 페이지가 0이 되는거 방지
        return 1;
      } else {
        return Math.ceil(this.cnt / 10);
        // (글 갯수/10)한 후 올림 연산을 통해 총 페이지 계산
      }
    },
  },
  watch: {
    $route(to, form) {
      if (to.path !== form.path) {
        console.log(this.$route.path, "주소오오오");
        console.log(this.$route.name, "이르르름");
        let path = this.$route.path.split("/");
        console.log(path, "pathhhhhh");
        this.video(path[1], this.$route.params.page - 1);
        console.log("to", to);
        console.log("form", form);
      }
    },
  },
  mounted() {
    console.log(this.$route);
    // 비디오 목록 받아오는 함수 실행
    this.video("", this.$route.params.page - 1);
  },
  methods: {
    // 비디오 리스트 받아오기
    async video(category, page) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll?kind=경매&page=${page}&vcate=${category}`,
        });
        console.log(res);
        this.videoList = res.data.data.auctionList;
        this.cnt = res.data.data.count;
      } catch (e) {
        console.log(e);
      }
    },

    //////  페이징 /////////
    //이전페이지 기능
    movetopreviouspage() {
      if (this.$route.params.page == 1) {
        alert("첫번째 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) - 1;
        this.$router.push({
          name: "all",
          params: { page: pp },
        });
        this.video("", this.$route.params.page - 2);
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: "all",
          params: { page: pp },
        });
        this.video("", this.$route.params.page);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#root {
  padding-top: 100px;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

#page-root {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#info-box {
  width: 100%;
  height: 500px;
  background-image: url("https://img.freepik.com/free-vector/flat-design-geometric-shapes-background_23-2148366514.jpg?w=1800&t=st=1680679389~exp=1680679989~hmac=dbe31b85c57b58440593c4b55a6a63bd47fa50b94f7d672f97ddc6ecc0f536f4");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  & > h2 {
    font-family: "ghanachoco";
    font-size: 5rem;
  }
}

@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translate3d(0, 100%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}

.test_obj {
  position: relative;
  animation: fadeInUp 1s;
}

.page-box {
  display: flex;
}

.hover-event {
  --duration: 0.44s;
  --move-hover: -4px;
  transform: translateY(var(--y)) translateZ(0);
  transition: transform var(--duration) ease, box-shadow var(--duration) ease;
  &:hover {
    --y: var(--move-hover);
    --shadow: var(--shadow-hover);
    span {
      --m: calc(var(--font-size) * -1);
    }
  }
}
</style>
