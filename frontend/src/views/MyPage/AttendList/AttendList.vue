<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="mypage">
        <div>
          <AttendItem :attendList="attendList" />
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
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";
import AttendItem from "./AttendItem.vue";

export default {
  components: { MypageNav, AttendItem },
  data() {
    return {
      attendList: "",
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
  mounted() {
    this.getList(this.$route.params.page - 1);
    this.getCnt();
  },
  methods: {
    async getList(page) {
      console.log("참여내역 불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/attendList?buyer=${this.$store.state.sessionStorageData.mno}&page=${page}`,
        });
        console.log(res);
        console.log(res.data);
        this.attendList = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    // 글 개수 가져오기
    async getCnt() {
      console.log("글 개수 가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionCount?kind=경매&seller=${this.$store.state.sessionStorageData.mno}`,
        });
        this.cnt = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    //이전페이지 기능
    movetopreviouspage() {
      if (this.$route.params.page == 1) {
        alert("첫번째 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) - 1;
        this.$router.push({
          name: "sellerauction",
          params: { page: pp },
        });
        this.getQna(this.$route.params.page - 2);
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: "sellerauction",
          params: { page: pp },
        });
        this.getQna(this.$route.params.page);
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
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
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

.page-box {
  display: flex;
}
</style>
