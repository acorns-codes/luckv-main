<template>
  <div class="container">
    <div>
      <div>
        <h2>공지사항</h2>
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
      <!-- 관리자만 보이게 해야함 -->
      <div v-show="this.$store.state.sessionStorageData.auth === 'A'">
        <v-btn color="#eee" @click="edit"> 수정 </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import { apiGetNoticeDetail, apiEditNotice } from "@/api/notice";
export default {
  data() {
    return {
      detaillData: "",
      editData: "",
      dialog: false,
    };
  },
  computed: {
    mno() {
      return this.$route.params.no;
    },
  },
  methods: {
    async getNoticeDetail() {
      const req = {
        nno: this.$route.params.no,
      };
      try {
        const res = await apiGetNoticeDetail(req);
        this.detaillData = res.data;
      } catch (error) {
        console.error(error);
      }
    },
    edit() {
      this.$router.push({
        name: "noticedetailEdit",
      });
    },
    deleteNotice() {
      console.log("삭제버튼");
    },
    async editNoticeDetail() {
      try {
        const noticeData = {
          no: this.$route.params.no,
          title: this.editData.title,
          content: this.editData.content,
          nid: this.$store.state.userdata.mno,
        };
        console.log(this.$store.state.userdata.mno);
        const res = await apiEditNotice(noticeData);
        if (res) {
          alert("공지 수정 완료!");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },

  async created() {
    await this.getNoticeDetail();
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
  margin: 20px;
}
</style>
