<template>
  <div id="root">
    <div id="info-box">
      <h2 class="test_obj hover-event">Subscription Video</h2>
    </div>
    <div id="page-root">
      <VideoCategory :category="category" />
      <VideoList :videoList="this.videoList" />
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
import VideoList from "@/components/video/VideoList.vue";
import VideoCategory from "@/components/video/VideoCategory.vue";

export default {
  components: { VideoList, VideoCategory },
  data() {
    return {
      videoList: "",
      cnt: "",
      defaultCnt: 10,
      page: "",
      category: [
        "구독전체",
        "subanimal",
        "subcharacter",
        "subbuilding",
        "subplant",
        "subetc",
      ],
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
        let pathList = this.$route.path.split("/");
        const path = pathList[1] === "sub" ? "" : pathList[1];
        this.video(path, this.$route.params.page - 1);
      }
    },
  },
  mounted() {
    this.subUser();
    // 구독 동영상 목록 함수 실행
    this.video("", this.$route.params.page - 1);
    // console.log(this.$store.state.subAuth);
  },
  methods: {
    // 구독자 확인
    subUser() {
      // console.log(this.$store.state.sessionStorageData.subYn);
      if (this.$store.state.sessionStorageData.subYn !== "Y") {
        alert("구독 회원을 위한 영상입니다! 사용을 원하시면 신청을 해주세요!");
      }
    },
    // 구독 동영상 목록 불러오기
    async video(category, page) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll?kind=구독&page=${page}&vcate=${category}`,
        });
        this.videoList = res.data.data.auctionList;
        this.cnt = res.data.data.count;
        console.log(this.videoList);
      } catch (e) {
        console.log(e);
      }
    },

    //////// 페이징 ///////
    //이전페이지 기능
    movetopreviouspage() {
      if (this.$route.params.page == 1) {
        alert("첫번째 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) - 1;
        this.$router.push({
          name: this.$router.name,
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
          name: this.$router.name,
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
  width: 1240px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.sub-box {
  width: inherit;
  height: 500px;
  border: 1px solid black;
}
#info-box {
  width: 100%;
  height: 500px;
  background-image: url("https://img.freepik.com/free-vector/background-seamless-pattern-vector-with-cute-memphis_53876-105506.jpg?w=1380&t=st=1680682686~exp=1680683286~hmac=49cd1539c8c42f4866a0f4e4d5f60c229160fb12995a949caf2a77b26592d1e2");
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
