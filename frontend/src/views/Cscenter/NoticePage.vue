<template>
  <div id="root">
    <div id="cscenter-root">
      <CsceterNav />
      <div class="cs-center">
        <div>
          <div>
            <h2>공지사항</h2>
          </div>

          <v-table>
            <thead>
              <tr style="font-weight: bolder">
                <th class="text-center">번호</th>
                <th class="text-center">제목</th>
                <th class="text-center">작성일</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in noticeList" :key="item.no">
                <td>{{ item.no }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.createAt }}</td>
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
        <!-- 관리자만 보일 수 있도록 설정해야함 -->
        <v-btn color="success" class="mt-2">공지사항 작성</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  data() {
    return {
      noticeList: [],
      cnt: "",
      defaultCnt: 10,
      page: "",
    };
  },
  components: { CsceterNav },

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
    this.getNotice();
    this.getNoticeCnt();
    console.log(this.$route.params.page);
  },
  methods: {
    // 공지사항 불러오기
    async getNotice() {
      console.log("공지사항 불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://localhost:80/noticeList`,
        });
        this.noticeList = res.data.data;
        this.page = this.cnt;
        console.log("cnt" + this.totalpage);
      } catch (error) {
        console.log(error);
      }
    },
    // 공지사항 글 개수 가져오기
    async getNoticeCnt() {
      console.log("공지사항 글 개수 가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://localhost:80/noticeCount`,
        });
        this.cnt = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    // 이전페이지 기능
    // movetopreviouspage() {
    //   if (this.$route.query.page == 1) {
    //     alert("첫번째 페이지입니다!");
    //   } else {
    //     let pp = this.$route.query.page - 1;
    //     this.$route.query.push({
    //       path: `/?page=${pp}`,
    //     });
    //   }
    // },
    // 다음페이지 기능
    // movetonextpage() {
    //   if (this.$route.query.page == Math.ceil(this.cnt / 10)) {
    //     alert("마지막 페이지입니다!");
    //   } else {
    //     let pp = this.$route.query.page - 1;
    //     console.log(pp);
    //   var pp = parseInt(this.$route.query.page) + 1;
    //   window.location.href = window.location.pathname + "?page=" + pp;
    // }
    // },
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
</style>
