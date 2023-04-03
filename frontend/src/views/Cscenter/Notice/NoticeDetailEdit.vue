<template>
  <div id="root">
    <div id="page-root">
      <!-- <CsceterNav /> -->
      <div class="container">
        <div>
          <div>
            <h2>공지사항</h2>
          </div>
          <v-table class="table-box">
            <thead>
              <tr>
                <th>제목</th>
                <td>
                  <v-text-field
                    variant="plain"
                    v-model="this.detaillData.title"
                  ></v-text-field>
                </td>
              </tr>
              <tr class="content">
                <th>내용</th>
                <td>
                  <v-textarea
                    variant="plain"
                    v-model="this.detaillData.content"
                  ></v-textarea>
                </td>
              </tr>
            </thead>
          </v-table>
        </div>
        <v-btn color="#eee" @click="editNotice"> 수정 </v-btn>
      </div>
    </div>
  </div>
</template>
<script>
// import CsceterNav from "@/components/CsceterNav.vue";
export default {
  // components: { CsceterNav },
  data() {
    return {
      detaillData: "",
      dialog: false,
    };
  },

  mounted() {
    let pn = this.$route.params.no;
    console.log(pn);
    this.getNoticeDetail();
    console.log(this.$store.state.sessionStorageData.mno);
  },
  methods: {
    async getNoticeDetail() {
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

    async editNotice() {
      console.log("공지수정");
      const editData = {
        no: this.$route.params.no,
        title: this.detaillData.title,
        content: this.detaillData.content,
        nid: this.$store.state.sessionStorageData.mno,
      };
      console.log(editData);
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/noticeUpdate`,
          data: editData,
        });
        if (res.data.data) {
          alert("게시글 수정이 완료되었습니다!");
          this.$router.push({
            name: "notice",
            params: { page: 1 },
          });
        } else {
          alert("게시글 수정을 할 수 없습니다!");
        }
        console.log(res);
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
  margin-top: 20px;
}
</style>
