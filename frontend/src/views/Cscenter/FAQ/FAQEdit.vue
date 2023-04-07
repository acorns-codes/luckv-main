<template>
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
                :items="categories"
                item-title="title"
                item-value="value"
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
</template>

<script>
export default {
  data() {
    return {
      FAQList: "",
      categories: [
        {
          title: "경매",
          value: "auction",
        },
        {
          title: "입찰",
          value: "bidding",
        },
        {
          title: "기타",
          value: "etc",
        },
      ],
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
          url: `${process.env.VUE_APP_API_URL}/frequentlyDetail?fno=${this.$route.params.fno}`,
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
          url: `${process.env.VUE_APP_API_URL}/frequentlyUpdate`,
          data: editData,
        });
        if (res.data.data) {
          alert("새로운 FAQ가 등록되었습니다!");
          this.$router.push({
            name: "faq",
            params: { page: 1 },
          });
        } else {
          alert("FAQ를 등록할 수 없습니다!");
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
