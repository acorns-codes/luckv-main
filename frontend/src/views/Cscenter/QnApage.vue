<template>
  <div class="cs-center">
    <div>
      <div>
        <h2>QnA</h2>
      </div>
      <div class="post_btn">
        <v-btn color="warning" @click="postQna"> QnA 등록 </v-btn>
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
        <tbody v-if="qnaList.length > 0">
          <template v-for="item in qnaList" :key="item.no">
            <tr class="event" @click="checkPwd(item)">
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
          </template>
        </tbody>
        <tbody v-else>
          <tr style="height: 300px">
            <td colspan="4">게시글이 없습니다.</td>
          </tr>
        </tbody>
      </v-table>
    </div>
    <Pagination
      v-if="qnaList.length != 0"
      @goPage="goPage"
      :pageNum="pageInfo.page"
      :pageSize="pageInfo.rowCnt"
      :totalPageCount="pageInfo.totalPageCnt"
    />
    <div class="page-box">
      <v-dialog v-model="dialog" width="500px">
        <v-card>
          <v-toolbar color="warning" title="비밀번호 확인"></v-toolbar>
          <v-card-text>
            <v-text-field
              prepend-inner-icon="mdi-lock-outline"
              v-model="password"
              type="password"
              variant="outlined"
            ></v-text-field>
          </v-card-text>

          <v-card-actions>
            <v-btn color="warning" width="50%" @click="dialog = false"
              >닫기</v-btn
            >
            <v-btn color="warning" width="50%" @click="qnaDetail">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import { apiGetQnaList } from "@/api/qna";
import Pagination from "@/components/PagiNationVue.vue";
export default {
  components: { Pagination },
  data() {
    return {
      reqModel: {
        page: 1,
        rowCnt: 20,
      },
      pageInfo: {},
      qnaList: [],
      dialog: false,
      password: "",
      selectedItem: null,
    };
  },
  async mounted() {
    await this.getQna();
  },
  methods: {
    // qna 불러오기
    async getQna(page) {
      const req = this.reqModel;
      if (page) {
        req.page = page;
      }
      try {
        const res = await apiGetQnaList(req);
        this.qnaList = res.list;
        this.pageInfo = res.pageInfo;
      } catch (error) {
        console.log(error);
      }
    },
    async goPage(page) {
      await this.getQna(page);
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
    // no, qpwd
    checkPwd(item) {
      console.log(item, "아아아");
      if (this.$store.state.sessionStorageData.auth == "A") {
        return this.$router.push({
          name: "qnadetail",
          params: { no: item.no },
        });
      }
      if (!item.qpwd) {
        return this.$router.push({
          name: "qnadetail",
          params: { no: item.no },
        });
      }
      this.dialog = true;
      this.selectedItem = item;
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
  watch: {
    pageInfo: {
      handler(info) {
        this.$router.push({
          name: "qna",
          query: { page: info.page },
        });
      },
      deep: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.cs-center {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  width: 100%;
  padding-top: 110px;
  & .post_btn {
    display: flex;
    justify-content: flex-end;
    padding-bottom: 30px;
  }
  & > div {
    width: 100%;
    & > div:nth-child(1) {
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}
.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
