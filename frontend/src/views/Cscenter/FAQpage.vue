<template>
  <div class="cs-center">
    <div>
      <div>
        <h2>자주 물어보는 질문</h2>
      </div>
      <div class="panels-container">
        <div class="button-box">
          <template v-for="item in categories" :key="item">
            <button
              :class="{ active: item.value == reqModel.category }"
              @click="reqModel.category = item.value"
            >
              {{ item.name }}
            </button>
          </template>
        </div>
        <!-- FAQ 리스트 -->
        <FAQList :FAQList="FAQList" />
        <Pagination
          v-if="FAQList.length != 0"
          @goPage="goPage"
          :pageNum="pageInfo.page"
          :pageSize="pageInfo.rowCnt"
          :totalPageCount="pageInfo.totalPageCnt"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { apiGetFaqList } from "@/api/faq";
import FAQList from "./FAQ/FAQList.vue";
import Pagination from "@/components/PagiNationVue.vue";
export default {
  components: { FAQList, Pagination },
  data() {
    return {
      reqModel: {
        page: 1,
        rowCnt: 20,
        category: "",
      },
      FAQList: [],
      categories: [
        {
          name: "전체",
          value: "",
        },
        {
          name: "경매",
          value: "auction",
        },
        {
          name: "입찰",
          value: "bidding",
        },
        {
          name: "기타",
          value: "etc",
        },
      ],
    };
  },
  async mounted() {
    await this.getFAQ();
  },
  watch: {
    "reqModel.category": {
      async handler() {
        await this.getFAQ();
      },
    },
    pageInfo: {
      handler(info) {
        this.$router.push({
          name: "faq",
          query: { page: info.page },
        });
      },
      deep: true,
    },
  },
  methods: {
    // FAQ 목록 불러오기
    async getFAQ(page) {
      const req = this.clone(this.reqModel);
      if (page) {
        req.page = page;
      }
      for (const key in req) {
        if (!req[key]) {
          delete req[key];
        }
      }
      try {
        const res = await apiGetFaqList(req);
        this.FAQList = res.list;
        this.pageInfo = res.pageInfo;
      } catch (error) {
        console.error(error);
      }
    },
    async goPage(page) {
      await this.getQna(page);
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
  width: 100%;
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
    padding: 3px 15px;
  }
  & > button.active {
    background-color: #ff9103;
    color: #fff;
  }
}
</style>
