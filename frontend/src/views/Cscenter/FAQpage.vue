<template>
  <CsceterNav />
  <div id="cs-center">
    <h1>자주 물어보는 질문</h1>
    <div class="panels-container">
      <div class="button-box">
        <button>All</button>
        <button>카테고리1</button>
        <button>카테고리2</button>
        <button>카테고리3</button>
      </div>
      <v-expansion-panels v-for="item in FAQList" :key="item">
        <v-expansion-panel :title="`${item.questions}`" :text="`${item.asked}`">
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  created() {
    this.getFAQ();
  },
  components: { CsceterNav },
  data() {
    return {
      panel: [0, 1],
      FAQList: "",
    };
  },
  methods: {
    async getFAQ() {
      console.log("faq불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://localhost:80/frequentlyList`,
        });
        console.log(res);
        this.FAQList = res.data.data;
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#cs-center {
  display: flex;
  flex-direction: column;
  align-items: center;
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
