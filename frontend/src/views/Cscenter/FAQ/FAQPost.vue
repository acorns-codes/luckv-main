<template>
  <div id="root">
    <div id="page-root">
      <CsceterNav />
      <div class="container">
        <div>
          <div>
            <h2>FAQ 등록</h2>
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
                <th>카테고리</th>
                <td>
                  <v-select
                    variant="plain"
                    :items="categoryList"
                    v-model="select"
                  ></v-select>
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
      categoryList: ["A", "B", "C", "D", "E", "F"],
      select: "A",
    };
  },
  methods: {
    async postQnA() {
      console.log("faq등록하기");
      const postData = {
        questions: this.title,
        asked: this.content,
        fid: this.$store.state.sessionStorageData.mno,
        category: this.select,
      };
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/insertFrequently`,
          data: postData,
        });
        if (res.data.data) {
          alert("새로운 FAQ가 등록되었습니다!");
          this.$router.push({
            name: "csfaq",
          });
        } else {
          alert("FAQ를 등록할 수 없습니다!");
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
