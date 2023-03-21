<template>
  <div id="root">
    <div id="mypage-root">
      <CsceterNav />
      <div class="container">
        <div>
          <div>
            <h2>QnA</h2>
          </div>
          <v-table class="table-box">
            <thead>
              <tr>
                <th>제목</th>
                <td>{{ detaillData.title }}</td>
              </tr>
              <tr>
                <th>내용</th>
                <td class="content">{{ detaillData.content }}</td>
              </tr>
            </thead>
          </v-table>
          <div>
            <v-btn color="#eee" @click="edit"> 수정 </v-btn>
            <v-btn color="#eee" @click="deleteNotice"> 삭제 </v-btn>
          </div>
          <CommentList />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CsceterNav from "@/components/CsceterNav.vue";
import CommentList from "./Comment/CommentList.vue";
export default {
  components: { CsceterNav, CommentList },
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
          url: `hhttp://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/questionDetail?qno=${this.$route.params.no}`,
        });
        this.detaillData = res.data.data;
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
  margin: 10px;
}
</style>
