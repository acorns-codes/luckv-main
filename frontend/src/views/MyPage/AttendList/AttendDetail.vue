<template>
  <div class="container">
    <h2>경매 낙찰 내역</h2>
    <div>
      <div class="page-box">
        <v-table class="table-box">
          <thead>
            <tr>
              <th>제목</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.title"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <tr class="content">
              <th>내용</th>
              <td>
                <v-textarea
                  rows="10"
                  variant="plain"
                  v-model="this.attendData.content"
                  readonly
                ></v-textarea>
              </td>
            </tr>
            <tr>
              <th>카테고리</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.vcate"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <!-- <tr>
                      <th>동영상</th>
                      <td>
                        <v-file-input
                          variant="plain"
                          prepend-icon="mdi-video"
                          v-model="video"
                          :rules="videoRules"
                          required
                        ></v-file-input>
                      </td>
                    </tr> -->

            <tr>
              <th>경매 시작가</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.payStart"
                  suffix="원"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th>낙찰가</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.payMax"
                  suffix="원"
                  readonly
                ></v-text-field>
              </td>
            </tr>

            <tr>
              <th>경매 시작 날짜</th>
              <td>
                <v-text-field
                  v-model="this.startDay[0]"
                  variant="plain"
                  type="date"
                  readonly
                ></v-text-field>
              </td>
            </tr>

            <tr>
              <th>경매 시작 시간</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.startDay[1]"
                  type="time"
                  readonly
                ></v-text-field>
              </td>
            </tr>

            <tr>
              <th>경매 마감 날짜</th>
              <td>
                <v-text-field
                  v-model="this.lastDay[0]"
                  variant="plain"
                  type="date"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th>경매 마감 시간</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.lastDay[1]"
                  type="time"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th>진행사항</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.status"
                  readonly
                ></v-text-field>
              </td>
            </tr>
          </thead>
        </v-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: false,
      attendData: "",
      startDay: "",
      lastDay: "",
    };
  },

  mounted() {
    // 상세 내역 불러오기
    this.getAttend();
    // console.log(this.$store.state.sessionStorageData);
  },
  methods: {
    // 각 경매의 상세페이지 받아오기
    async getAttend() {
      console.log("경매조회");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${this.$route.params.ano}`,
        });
        console.log(res);
        this.attendData = res.data.data;
        this.startDay = this.attendData.startDay.split(" ");
        this.lastDay = this.attendData.lastDay.split(" ");
      } catch (error) {
        console.log(error);
      }
    },
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
    justify-content: center;

    /* flex-direction: column; */
    display: flex;
    /* align-items: center; */

    padding-bottom: 10px;
  }
}
.table-box {
  width: 700px;
  margin: 10px;
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
  height: 200px;
  /* vertical-align: top; */
}

.video-box {
  width: 400px;
  height: 300px;
  background-color: antiquewhite;
  margin: 0 auto;
  margin-bottom: 30px;
}

button {
  margin-top: 20px;
}
</style>
