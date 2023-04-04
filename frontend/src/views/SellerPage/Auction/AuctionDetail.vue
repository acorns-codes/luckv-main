<template>
  <div class="container">
    <h2>경매 상세</h2>
    <div>
      <div class="page-box">
        <v-table class="table-box">
          <thead>
            <tr>
              <th>동영상</th>
              <td colspan="3">
                <div class="video-box">
                  <video
                    controls
                    @mouseover="playVideo"
                    @mouseleave="stopVideo"
                    :src="`${videoSrc}/videoplay?ano=${this.auctionData.ano}`"
                  ></video>
                </div>
              </td>
            </tr>
            <tr>
              <th>제목</th>
              <td colspan="3">
                <v-text-field
                  variant="plain"
                  v-model="this.auctionData.title"
                  readonly
                ></v-text-field>
              </td>
            </tr>

            <tr class="content">
              <th>내용</th>
              <td colspan="3">
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
              <td colspan="3">
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
            <tr>
              <th>경매 상태</th>
              <td>
                <v-text-field
                  v-model="this.auctionData.status"
                  variant="plain"
                  readonly
                ></v-text-field>
              </td>
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
        <v-dialog
          v-if="this.auctionData.status === '판매종료'"
          v-model="dialog"
          persistent
          width="500"
        >
          <template v-slot:activator="{ props }">
            <v-btn class="mt-2" v-bind="props"> 구독으로 변경 </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">구독 제공 기간 설정</span>
            </v-card-title>
            <v-card-text>
              <p>기간 설정</p>
              <v-form v-model="valid" @submit.prevent="auctionChange">
                <v-text-field
                  v-model="date"
                  type="date"
                  :rules="dateRules"
                ></v-text-field>
                <v-text-field
                  v-model="time"
                  type="time"
                  :rules="timeRules"
                ></v-text-field>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="blue-darken-1"
                    variant="text"
                    @click="dialog = false"
                  >
                    닫기
                  </v-btn>
                  <v-btn type="submit" color="blue-darken-1" variant="text">
                    입찰
                  </v-btn>
                </v-card-actions>
              </v-form>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-btn v-else @click="edit" class="mt-2">수정</v-btn>
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
      dialog: false,
      date: "",
      time: "",
      dateRules: [(v) => !!v || "날짜를 입력하셔야합니다!"],
      timeRules: [(v) => !!v || "시간을 입력하셔야합니다!"],
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
    this.videoSrc = process.env.VUE_APP_API_URL;
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
    // 경매 수정 버튼
    edit() {
      this.$router.push({
        name: "editauction",
        params: { ano: this.$route.params.ano },
      });
    },
    // 판매 종료 후 구독으로 변경
    async auctionChange() {
      console.log("구독으로 변경");
      if (!this.valid) {
        alert("날짜를 확인해주세요!");
      } else {
        const editDate = `${this.date} ${this.time}:00`;
        const editdata = {
          ano: this.$route.params.ano,
          seller: this.$store.state.sessionStorageData.mno,
          kind: this.auctionData.kind,
          lastDay: editDate,
        };
        console.log(editdata);
        try {
          const res = await this.$axios({
            headers: {
              "Content-type": "application/json",
            },
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/auctionChange`,
            data: editdata,
          });
          console.log(editdata);
          console.log(res.data.data);
          if (res.data.data) {
            alert("구독으로 변경하였습니다!");
            this.$router.push({
              name: "sellerauction",
              params: { page: 1 },
            });
          }
        } catch (error) {
          console.log(error);
        }
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
  font-size: 0.9rem;
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
  width: 520px;
  height: 360px;
  margin: 10px;
  & > video {
    width: inherit;
    height: inherit;
    object-fit: cover;
  }
}

button {
  margin-top: 20px;
}
</style>
