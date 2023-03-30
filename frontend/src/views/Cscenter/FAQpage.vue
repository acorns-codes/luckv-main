<template>
  <div id="root">
    <div id="cscenter-root">
      <CsceterNav />
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
                @click="getFAQ(item.url)"
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
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
import FAQList from "./FAQ/FAQList.vue";
export default {
  components: { CsceterNav, FAQList },
  data() {
    return {
      FAQList: "",
      categories: [
        {
          name: "전체",
          value: "all",
          url: "",
        },
        {
          name: "경매",
          value: "auction",
          url: "?category=auction",
        },
        {
          name: "입찰",
          value: "bidding",
          url: "?category=bidding",
        },
        {
          name: "기타",
          value: "etc",
          url: "?category=etc",
        },
      ],
    };
  },
  mounted() {
    this.getFAQ("");
  },
  methods: {
    // FAQ 목록 불러오기
    async getFAQ(categoty) {
      console.log("faq불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/frequentlyList${categoty}`,
        });
        console.log(res);
        this.FAQList = res.data.data;
      } catch (error) {
        console.log(error);
      }
    },
    // 새로운 FAQ 등록
    postFAQ() {
      this.$router.push({
        path: "/postfaq",
      });
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
#cscenter-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
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
