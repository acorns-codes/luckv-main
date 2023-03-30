<template>
  <div id="root">
    <div id="cscenter-root">
      <CsceterNav />
      <div class="cs-center">
        <div>
          <div>
            <h2>QnA</h2>
          </div>
          <v-table>
            <thead>
              <tr style="font-weight: bolder">
                <th class="text-center">글번호</th>
                <th class="text-center">제목</th>
                <th class="text-center">작성자</th>
                <th class="text-center">작성일</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="item in qnaList"
                :key="item.no"
                class="event"
                @click="qnaDetail(item.no)"
              >
                <td>{{ item.no }}</td>
                <td style="width: 400px">{{ item.title }}</td>
                <td>{{ item.id }}</td>
                <td>{{ item.qcreate }}</td>
              </tr>
            </tbody>
          </v-table>
        </div>
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
        <v-btn color="success" class="mt-2" @click="postQna">QnA 작성</v-btn>
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
      qnaList: [],
      cnt: "",
      defaultCnt: 10,
      page: "",
    };
  },
  // 계산 목적으로
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
  // 페이지가 켜질 때 실행
  mounted() {
    this.getQna(this.$route.params.page - 1);
  },
  methods: {
    // qna 불러오기
    async getQna(page) {
      console.log("qna 러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/questionPage?page=${page}`,
        });
        console.log(res.data);
        this.qnaList = res.data.data.questionList;
        this.cnt = res.data.data.count;
        this.page = this.cnt;
        // console.log("cnt" + this.totalpage);
      } catch (error) {
        console.log(error);
      }
    },
    // qna 글 개수 가져오기
    async getQnaCnt() {
      console.log("qna 글 개수 가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/questionCount`,
        });
        this.cnt = res.data;
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
          name: "csqna",
          params: { page: pp },
        });
        this.getQna(this.$route.params.page - 2);
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: "csqna",
          params: { page: pp },
        });
        this.getQna(this.$route.params.page);
      }
    },

    // 상세페이지로 이동
    qnaDetail(no) {
      console.log(no);
      this.$router.push({
        name: "csqnadetail",
        params: { no: no },
      });
    },
    // 글 작성기능
    postQna() {
      this.$router.push({
        path: "/postqna",
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

.page-box {
  display: flex;
}

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
