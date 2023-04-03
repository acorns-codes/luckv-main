<template>
  <div id="root">
    <div id="page-root">
      <CsceterNav />
      <div class="container">
        <div>
          <div>
            <h2>QnA 등록</h2>
          </div>
          <v-table class="table-box">
            <thead>
              <tr>
                <th>제목</th>
                <td>
                  <v-text-field
                    placeholder="제목을 입력하세요"
                    variant="plain"
                    v-model="title"
                  ></v-text-field>
                </td>
              </tr>
              <tr>
                <th>비밀글</th>
                <td>
                  <v-radio-group inline v-model="auth" required>
                    <v-radio label="비밀글" value="A"></v-radio>
                    <v-radio label="공개글" value="B"></v-radio>
                  </v-radio-group>
                  <v-text-field
                    v-if="auth === 'A'"
                    prepend-inner-icon="mdi-lock-outline"
                    v-model="password"
                    type="password"
                    variant="outlined"
                    placeholder="4자리의 숫자로 입력해주세요"
                    :rules="pwRules"
                  ></v-text-field>
                </td>
              </tr>

              <tr class="content">
                <th>내용</th>
                <td>
                  <v-textarea
                    rows="20"
                    placeholder="내용을 입력하세요"
                    variant="plain"
                    v-model="content"
                  ></v-textarea>
                </td>
              </tr>
            </thead>
          </v-table>
        </div>
        <v-btn @click="postQnA">등록</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  components: {
    CsceterNav,
  },
  data() {
    return {
      title: "",
      content: "",
      auth: "A",
      password: "",
      pwRules: [
        (v) => !!v || "패스워드는 필수 입력사항입니다.",
        (v) => /[0-9]/.test(v) || "숫자만 입력해주세요.",
        (v) => v.length === 4 || "4자리로 입력해주세요",
      ],
    };
  },
  mounted() {
    console.log(this.$store.state.sessionStorageData.mno);
  },
  methods: {
    async postQnA() {
      console.log("qna등록합니당");
      const qnaData = {
        title: this.title,
        content: this.content,
        qid: this.$store.state.sessionStorageData.mno,
        qpwd: this.password,
      };
      console.log(qnaData);
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/insertQuestion`,
          data: qnaData,
        });
        if (res.data.data) {
          alert("QnA등록에 성공하였습니다.");
          this.$router.push({
            name: "csqna",
            params: { page: 1 },
          });
        } else {
          alert("QnA등록에 실패하였습니다.");
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
