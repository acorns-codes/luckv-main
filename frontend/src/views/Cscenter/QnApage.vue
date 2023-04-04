<template>
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
            @click="checkPwd(item.no, item.qpwd)"
          >
            <td>{{ item.no }}</td>
            <td style="width: 400px">
              <v-icon size="x-small" v-if="item.qpwd !== null">
                mdi-lock-outline
              </v-icon>
              {{ item.title }} ({{ item.reply }})
            </td>
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
      <v-dialog v-model="dialog" width="500px">
        <v-card>
          <v-toolbar color="success" title="비밀번호 확인"></v-toolbar>
          <v-card-text>
            <v-text-field
              prepend-inner-icon="mdi-lock-outline"
              v-model="password"
              type="password"
              variant="outlined"
            ></v-text-field>
          </v-card-text>

          <v-card-actions>
            <v-btn color="success" width="50%" @click="dialog = false"
              >닫기</v-btn
            >
            <v-btn color="success" width="50%" @click="qnaDetail">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      qnaList: [],
      cnt: "",
      defaultCnt: 10,
      page: "",
      dialog: false,
      password: "",
      selectedItem: null,
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
    qnaDetail() {
      if (this.password === this.selectedItem.qpwd) {
        this.$router.push({
          name: "qnadetail",
          params: { no: this.selectedItem.no },
        });
      } else {
        // 비밀번호가 틀린 경우
        alert("비밀번호가 틀렸습니다.");
        this.password = null;
      }
    },

    // 비밀번호 체크
    checkPwd(no, qpwd) {
      console.log(no);
      if (qpwd !== null) {
        this.dialog = true;
        this.selectedItem = { no, qpwd }; // 데이터 담기
      } else {
        this.$router.push({
          name: "qnadetail",
          params: { no: no },
        });
      }
    },
    // 비밀번호 확인 모달 닫기
    closeModeal() {
      this.dialog = false;
      this.password = null;
    },
    // 글 작성기능
    postQna() {
      this.$router.push({
        name: "postqna",
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

.page-box {
  display: flex;
}

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
