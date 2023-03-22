<template>
  <div id="root">
    <div id="page-root">
      <CsceterNav />
      <div class="container">
        <div>
          <div>
            <h2>FAQ 수정</h2>
          </div>
          <v-table class="table-box">
            <thead>
              <tr>
                <th>제목</th>
                <td>
                  <v-text-field
                    variant="plain"
                    v-model="this.FAQList.questions"
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <th>카테고리</th>
                <td>
                  <v-select
                    variant="plain"
                    :items="categoryList"
                    v-model="this.FAQList.category"
                  ></v-select>
                </td>
              </tr>
              <tr class="content">
                <th>내용</th>
                <td>
                  <v-textarea
                    rows="20"
                    variant="plain"
                    v-model="this.FAQList.asked"
                  ></v-textarea>
                </td>
              </tr>
            </thead>
          </v-table>
        </div>
        <v-btn @click="editQnA">수정</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  components: {
    CsceterNav,
  },
  data() {
    return {
      FAQList: "",
      categoryList: ["A", "B", "C", "D", "E", "F"],
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
          url: `http://localhost:80/frequentlyDetail?fno=${this.$route.params.fno}`,
        });
        this.FAQList = res.data;
        // console.log(this.FAQList);
      } catch (error) {
        console.log(error);
      }
    },
    async editQnA() {
      console.log("faq수정하기");
      const editData = {
        questions: this.FAQList.questions,
        asked: this.FAQList.asked,
        fno: this.$route.params.fno,
        category: this.FAQList.category,
      };
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          url: `http://localhost:80/frequentlyUpdate`,
          data: editData,
        });
        if (res.data.data) {
          alert("FAQ가 수정되었습니다!");
          this.$router.push({
            name: "csfaq",
          });
        } else {
          alert("FAQ를 수정할 수 없습니다!");
        }
        console.log(res);
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
#page-root {
  width: 1440px;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    & > div:nth-child(1) {
      width: 100%;
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}
.table-box {
  border-top: 1px solid #343434;
  th {
    width: 150px;
  }
  th,
  td {
    border-bottom: 1px solid #eee;
    padding: 10px 10px;
    text-align: left;
  }
}
.content {
  height: 500px;
  /* vertical-align: top; */
}

button {
  margin-top: 20px;
}
</style>
