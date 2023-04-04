<template>
  <div class="container">
    <h2>경매 상세</h2>
    <div>
      <div class="page-box">
        <v-table class="table-box">
          <thead>
            <tr>
              <th>제목</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.auctionData.title"
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
                  v-model="this.auctionData.content"
                  readonly
                ></v-textarea>
              </td>
            </tr>
            <tr>
              <th>카테고리</th>
              <td>
                <v-select
                  variant="plain"
                  required
                  :items="categorys"
                  v-model="this.auctionData.vcate"
                  item-title="title"
                  item-value="value"
                  readonly
                ></v-select>
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
                  v-model="this.auctionData.payStart"
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
          </thead>
        </v-table>

        <v-btn @click="edit" class="mt-2">수정</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: false,
      auctionData: "",
      startDay: "",
      lastDay: "",
      categorys: [
        {
          title: "동물",
          value: "animal",
        },
        {
          title: "인물",
          value: "character",
        },
        {
          title: "건물",
          value: "building",
        },
        {
          title: "식물",
          value: "plant",
        },
        {
          title: "기타",
          value: "etc",
        },
      ],
    };
  },
  computed() {},

  mounted() {
    // 상세 내역 불러오기
    this.getAuction();
    console.log(this.$store.state.sessionStorageData);
  },
  methods: {
    // 각 경매의 상세페이지 받아오기
    async getAuction() {
      console.log("경매조회");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${this.$route.params.ano}`,
        });
        console.log(res);
        this.auctionData = res.data.data;
        this.startDay = this.auctionData.startDay.split(" ");
        this.lastDay = this.auctionData.lastDay.split(" ");
        this.auctionData.payStart = this.$globalFuc(this.auctionData.payStart);
      } catch (error) {
        console.log(error);
      }
    },
    edit() {
      this.$router.push({
        name: "editauction",
        params: { ano: this.$route.params.ano },
      });
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
