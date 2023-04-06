<template>
  <div class="mypage">
    <div>
      <div class="button-box"></div>
      <VideoItem :auctionList="auctionList" />
    </div>
    <!-- 페이징 -->
    <div class="page-box">
      <button @click="movetopreviouspage">
        <v-icon> mdi-chevron-left </v-icon>
      </button>
      <div>{{ this.$route.params.page }} / {{ totalpage }}</div>
      <button @click="movetonextpage">
        <v-icon> mdi-chevron-right </v-icon>
      </button>
    </div>
  </div>
</template>

<script>
import VideoItem from "./VideoItem.vue";

export default {
  components: { VideoItem },
  data() {
    return {
      auctionList: "",
      cnt: "",
      defaultCnt: 10,
      page: "",
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
        this.getVideo(this.$route.name, this.$route.params.page - 1);
        console.log(this.$route.name, "주소오오오");
      }
    },
  },

  mounted() {
    // const path = this.$route.path === ""
    console.log(this.$route);
    this.getVideo(this.$route.name, this.$route.params.page - 1);
  },
  methods: {
    async getVideo(category, page) {
      console.log("동영상목록 불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionPage?seller=${this.$store.state.sessionStorageData.mno}?page=${page}&kind=${category}`,
        });

        console.log(res.data);
        this.auctionList = res.data.data.auctionList;
        this.cnt = res.data.data.count;
      } catch (error) {
        console.log(error);
      }
    },

    // 이전페이지 기능
    movetopreviouspage() {
      if (this.$route.params.page == 1) {
        alert("첫번째 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) - 1;
        this.$router.push({
          name: this.$route.name,
          params: { page: pp },
        });
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: this.$route.name,
          params: { page: pp },
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.mypage {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    & > div:nth-child(1) {
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
