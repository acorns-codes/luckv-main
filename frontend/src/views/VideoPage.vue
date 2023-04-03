<template>
  <div id="root">
    <div id="page-root">
      <div class="button-box">
        <button
          v-for="(item, index) in categorys"
          :key="index"
          @click="this.video(item.url, this.$route.params.page - 1)"
        >
          {{ item.title }}
        </button>
      </div>
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
export default {
  components: { VideoList },
  data() {
    return {
      videoList: "",
      cnt: "",
      defaultCnt: 10,
      page: "",
      categorys: [
        { title: "ALL", value: "", url: "" },
        {
          title: "동물",
          value: "animal",
          url: "vcate=animal",
        },
        {
          title: "인물",
          value: "character",
          url: "vcate=character",
        },
        {
          title: "건물",
          value: "building",
          url: "vcate=building",
        },
        {
          title: "식물",
          value: "plant",
          url: "vcate=plant",
        },
        {
          title: "기타",
          value: "etc",
          url: "vcate=etc",
        },
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
  mounted() {
    // 비디오 목록 받아오는 함수 실행
    this.video("", this.$route.params.page - 1);
  },
  watch: {
    $route: "video",
  },
  methods: {
    // 비디오 리스트 받아오기
    async video(category, page) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll?${category}&page=${page}&kind=경매`,
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
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}

#page-root {
  width: 1440px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.button-box {
  width: 400px;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  & > button {
    color: #343434;
    background-color: #f4f4f4;
    border-radius: 10px;
    padding: 2px 10px 2px 10px;
  }
}

.page-box {
  display: flex;
}
</style>
