<template>
  <div id="root">
    <div id="page-root">
      <CsceterNav />
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
          <div>
            <v-btn color="#eee" @click="edit"> 수정 </v-btn>
          </div>
        </div>
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
      detaillData: "",
      editData: "",
      dialog: false,
    };
  },

  mounted() {
    console.log(this.$route.params.no);
    let pn = this.$route.params.no;
    console.log(pn);
    this.getNoticeDetail();
  },
  methods: {
    async getNoticeDetail() {
      console.log("내용가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/noticeDetail?nno=${this.$route.params.no}`,
        });
        this.detaillData = res.data.data;
      } catch (error) {
        console.log(error);
      }
    },
    edit() {
      this.$router.push({
        name: "contentEdit",
      });
    },
    deleteNotice() {
      console.log("삭제버튼");
    },
    async editNoticeDetail() {
      console.log("공지사항 수정");
      try {
        const noticeData = {
          no: this.$route.params.no,
          title: this.editData.title,
          content: this.editData.content,
          nid: this.$store.state.userdata.mno,
        };
        console.log(this.$store.state.userdata.mno);
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/noticeUpdate`,
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
#page-root {
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
  margin: 20px;
}
</style>
