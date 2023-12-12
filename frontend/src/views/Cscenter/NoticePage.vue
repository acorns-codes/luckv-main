<template>
  <div class="cs-center">
    <div>
      <div>
        <h2>공지사항</h2>
      </div>
      <v-table>
        <thead>
          <tr style="font-weight: bolder">
            <th class="text-center">글번호</th>
            <th class="text-center">제목</th>
            <th class="text-center">작성일</th>
          </tr>
        </thead>
        <tbody v-if="noticeList.length > 0">
          <template v-for="item in noticeList" :key="item.no">
            <tr class="event" @click="noticeContent(item.no)">
              <td>{{ item.no }}</td>
              <td>
                {{ item.title }}
              </td>
              <td>{{ item.createAt }}</td>
            </tr>
          </template>
        </tbody>
        <tbody v-else>
          <tr style="height: 300px">
            <td colspan="3">게시글이 없습니다.</td>
          </tr>
        </tbody>
      </v-table>
    </div>
    <Pagination
      v-if="noticeList.length != 0"
      @goPage="goPage"
      :pageNum="pageInfo.page"
      :pageSize="pageInfo.rowCnt"
      :totalPageCount="pageInfo.totalPageCnt"
    />
    <!-- 관리자만 공지사항 등록 버튼 보임-->
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent width="500">
        <template v-slot:activator="{ props }">
          <v-btn
            v-show="this.$store.state.sessionStorageData.auth === 'A'"
            color="warning"
            v-bind="props"
          >
            공지사항 등록
          </v-btn>
        </template>
        <v-card>
          <h3 style="padding: 10px">공지사항 등록</h3>
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
            <v-btn color="blue-darken-1" variant="text" @click="dialog = false">
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
</template>

<script>
import { apiGetNoticeList, apiAddNotice } from "@/api/notice";
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
      noticeList: [],
      dialog: false,
      title: "",
      content: "",
    };
  },
  async created() {
    await this.getNotice();
  },
  methods: {
    // 공지사항 불러오기
    async getNotice(page) {
      const req = this.reqModel;
      if (page) {
        req.page = page;
      }
      try {
        const res = await apiGetNoticeList(req);
        this.noticeList = res.list;
        this.pageInfo = res.pageInfo;
      } catch (error) {
        console.log(error);
      }
    },
    async goPage(page) {
      this.getNotice(page);
    },
    // 상세페이지로 이동
    noticeContent(no) {
      console.log(no);
      this.$router.push({
        name: "noticedetail",
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
        const res = await apiAddNotice(noticeData);
        if (res.data) {
          this.getNotice(1);
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  watch: {
    pageInfo: {
      handler(info) {
        this.$router.push({
          name: "notice",
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

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
