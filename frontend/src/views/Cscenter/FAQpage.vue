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
              <button>All</button>
              <button>카테고리1</button>
              <button>카테고리2</button>
              <button>카테고리3</button>
            </div>
            <v-expansion-panels>
              <v-expansion-panel v-for="item in FAQList" :key="item">
                <v-expansion-panel-title>
                  <v-row>
                    <v-col>{{ item.questions }}</v-col>
                  </v-row>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-expansion-panel-text>{{
                    item.asked
                  }}</v-expansion-panel-text>
                  <v-card-actions
                    v-show="this.$store.state.sessionStorageData.auth === 'A'"
                  >
                    <v-spacer></v-spacer>

                    <v-btn
                      variant="text"
                      color="#343434"
                      @click="editFAQ(item.fno)"
                      >수정</v-btn
                    >
                    <v-btn
                      variant="text"
                      color="#343434"
                      @click="deleteFAQ(item.fno)"
                      >삭제</v-btn
                    >
                  </v-card-actions>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
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
export default {
  components: { CsceterNav },
  data() {
    return {
      panel: [0, 1],
      FAQList: "",
    };
  },
  created() {
    this.getFAQ();
  },
  methods: {
    async getFAQ() {
      console.log("faq불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/frequentlyList`,
        });
        console.log(res);
        this.FAQList = res.data.data;
      } catch (error) {
        console.log(error);
      }
    },
    postFAQ() {
      this.$router.push({
        path: "/postfaq",
      });
    },
    editFAQ(fno) {
      this.$router.push({
        name: "editfaq",
        params: { fno: fno },
      });
    },
    async deleteFAQ(fno) {
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/frequentlyDelete?fno=${fno}`,
        });
        if (res.data.data) {
          alert("FAQ가 삭제되었습니다.");
          window.location.reload();
        } else {
          alert("FAQ 삭제를 실패했습니다");
        }
      } catch (error) {
        console.log(error);
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
