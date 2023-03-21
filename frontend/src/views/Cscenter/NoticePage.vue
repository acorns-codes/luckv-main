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
              <tr
                v-for="item in noticeList"
                :key="item.no"
                class="event"
                @click="noticeContent(item.no)"
              >
                <td>{{ item.no }}</td>
                <td>
                  {{ item.title }}
                </td>
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
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent width="500">
            <template v-slot:activator="{ props }">
              <v-btn color="success" v-bind="props"> 공지사항 등록 </v-btn>
            </template>
            <v-card>
              <h3>공지사항 등록</h3>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="제목"
                        v-model="title"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-textarea
                        label="내용"
                        v-model="content"
                        required
                      ></v-textarea>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="dialog = false"
                >
                  Close
                </v-btn>
                <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="[(dialog = false), postNotice()]"
                >
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
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
      dialog: false,
      title: "",
      content: "",
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
    this.getNotice(this.$route.params.page - 1);
    this.getNoticeCnt();
  },
  methods: {
    // 공지사항 불러오기
    async getNotice(page) {
      console.log("공지사항 불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://localhost:80/noticePage?page=${page}`,
        });
        // console.log(res.data);
        this.noticeList = res.data;
        // console.log(this.noticeList);
        this.page = this.cnt;
        // console.log("cnt" + this.totalpage);
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
    //이전페이지 기능
    movetopreviouspage() {
      if (this.$route.params.page == 1) {
        alert("첫번째 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) - 1;
        this.$router.push({
          name: "cscenter",
          params: { page: pp },
        });
        this.getNotice(this.$route.params.page - 2);
      }
    },
    // 다음페이지 기능
    movetonextpage() {
      if (this.$route.params.page == Math.ceil(this.cnt / 10)) {
        alert("마지막 페이지입니다!");
      } else {
        let pp = parseInt(this.$route.params.page) + 1;
        this.$router.push({
          name: "cscenter",
          params: { page: pp },
        });
        this.getNotice(this.$route.params.page);
      }
    },

    // 상세페이지로 이동
    noticeContent(no) {
      console.log(no);
      this.$router.push({
        name: "content",
        params: { no: no },
      });
    },
    // 새로운 공지사항 등록
    async postNotice() {
      try {
        const noticeData = {
          title: this.title,
          content: this.content,
          nid: this.$store.state.sessionStorageData.mno,
        };
        console.log(this.$store.state.userdata);
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: "http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/insertNotice",
          data: noticeData,
        });
        console.log(res);
        console.log(noticeData);
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

.page-box {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
