<template>
  <div class="cs-center">
    <div>
      <div>
        <h2>자주 물어보는 질문</h2>
      </div>
      <div class="panels-container">
        <div class="button-box">
          <button
            v-for="item in categories"
            :key="item"
            @click="getFAQ(item.url, this.$route.params.page - 1)"
          >
            {{ item.name }}
          </button>
        </div>
        <!-- FAQ 리스트 -->
        <FAQList :FAQList="FAQList" />
        <v-btn
          style="margin: 20px"
          v-show="this.$store.state.sessionStorageData.auth === 'A'"
          color="success"
          @click="postFAQ"
          >FAQ등록</v-btn
        >
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
  </div>
</template>

<script>
import FAQList from "./FAQ/FAQList.vue";
export default {
  components: { FAQList },
  data() {
    return {
      FAQList: "",
      cnt: "",
      defaultCnt: 10,
      page: "",
      categories: [
        {
          name: "전체",
          value: "all",
          url: "",
        },
        {
          name: "경매",
          value: "auction",
          url: "category=auction",
        },
        {
          name: "입찰",
          value: "bidding",
          url: "category=bidding",
        },
        {
          name: "기타",
          value: "etc",
          url: "category=etc",
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
    this.getFAQ("", this.$route.params.page - 1);
  },
  methods: {
    // FAQ 목록 불러오기
    async getFAQ(category, page) {
      console.log("faq불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/frequentlyPage?${category}&page=${page}`,
        });
        console.log(res);
        this.FAQList = res.data.data.frequentlyList;
        this.cnt = res.data.data.count;
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
          name: "csfaq",
          params: { page: pp },
        });
        this.getNotice(this.$route.params.page - 2);
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: "faq",
          params: { page: pp },
        });
        this.getNotice(this.$route.params.page);
      }
    },
    // 새로운 FAQ 등록
    postFAQ() {
      this.$router.push({
        name: "postfaq",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.cs-center {
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
.panels-container {
  width: 960px;
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
</style>
