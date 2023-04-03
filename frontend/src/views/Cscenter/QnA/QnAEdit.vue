<template>
  <div class="container">
    <div>
      <div>
        <h2>QnA 수정</h2>
      </div>
      <v-table class="table-box">
        <thead>
          <tr>
            <th>제목</th>
            <td>
              <v-text-field
                variant="plain"
                v-model="this.detaillData.title"
              ></v-text-field>
            </td>
          </tr>
          <tr class="content">
            <th>내용</th>
            <td>
              <v-textarea
                rows="20"
                placeholder="내용을 입력하세요"
                variant="plain"
                v-model="this.detaillData.content"
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
      detaillData: "",
    };
  },
  mounted() {
    this.getQnADetail();
  },
  methods: {
    async getQnADetail() {
      console.log("내용가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/questionDetail?qno=${this.$route.params.no}`,
        });
        this.detaillData = res.data.data;
      } catch (error) {
        console.log(error);
      }
    },
    async editQnA() {
      console.log("수정하기");
      const editData = {
        title: this.detaillData.title,
        content: this.detaillData.content,
        no: this.$route.params.no,
      };
      console.log(editData);
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/questionUpdate`,
          data: editData,
        });
        console.log(res);
        if (res.data.data) {
          alert("QnA가 수정되었습니다!");
          this.$router.push({
            name: "qna",
            params: { page: 1 },
          });
        }
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
