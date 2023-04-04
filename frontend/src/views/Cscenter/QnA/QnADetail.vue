<template>
  <div class="container">
    <div>
      <div>
        <h2>QnA</h2>
      </div>
      <!-- 작성자만 보일 수 있도록 설정해야 함 -->
      <div class="btn-box">
        <v-btn size="small" color="#eee" @click="editBtn"> 수정 </v-btn>
        <v-btn size="small" color="#eee" @click="deleteBtn"> 삭제 </v-btn>
      </div>
      <v-table class="table-box">
        <thead>
          <tr>
            <th>제목</th>
            <td>{{ detaillData.title }}</td>
          </tr>
          <tr class="content">
            <th>내용</th>
            <td>{{ detaillData.content }}</td>
          </tr>
        </thead>
      </v-table>
      <CommentList />
    </div>
  </div>
</template>
<script>
import CommentList from "./Comment/CommentList.vue";
export default {
  components: { CommentList },
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
          url: `${process.env.VUE_APP_API_URL}/questionDetail?no=${this.$route.params.no}`,
        });
        this.detaillData = res.data.data;
        console.log(this.detaillData);
      } catch (error) {
        console.log(error);
      }
    },
    editBtn() {
      this.$router.push({
        name: "qnadetailEdit",
      });
    },
    async deleteBtn() {
      console.log("QnA삭제");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/questionDelete?qno=${this.$route.params.no}`,
        });
        if (res.data.data) {
          alert("QnA가 삭제되었습니다!");
          this.$router.push({
            name: "qna",
            params: { page: 1 },
          });
        } else {
          alert("QnA 삭제에 실패하였습니다");
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
#mypage-root {
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

.btn-box {
  display: flex;
  justify-content: end;
}

.table-box {
  border-top: 1px solid #343434;
  border-bottom: 1px solid #343434;

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
  margin: 10px;
}
</style>
