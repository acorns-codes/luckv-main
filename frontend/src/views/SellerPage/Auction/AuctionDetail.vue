<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="container">
        <h2>경매 상세 페이지</h2>
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
                      readonly
                      v-model="this.auctionData.content"
                    ></v-textarea>
                  </td>
                </tr>
                <tr>
                  <th>카테고리</th>
                  <td>
                    <v-text-field
                      variant="plain"
                      readonly
                      v-model="this.auctionData.vcate"
                    ></v-text-field>
                  </td>
                </tr>
                <template v-if="this.auctionData.kind === '경매'">
                  <tr>
                    <th>경매 시작가</th>
                    <td>
                      <v-text-field
                        variant="plain"
                        readonly
                        v-model="this.auctionData.payStart"
                        suffix="원"
                      ></v-text-field>
                    </td>
                  </tr>
                  <tr>
                    <th>경매 시작 날짜</th>
                    <td>
                      <v-text-field
                        v-model="this.startDay[0]"
                        variant="plain"
                        readonly
                        type="date"
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
                        readonly
                        type="date"
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
                </template>
                <template v-else-if="this.auctionData.kind === '구독'">
                  <tr>
                    <th>구독 마감 날짜</th>
                    <td>
                      <v-text-field
                        v-model="this.lastDay[0]"
                        variant="plain"
                        readonly
                        type="date"
                      ></v-text-field>
                    </td>
                  </tr>
                  <tr>
                    <th>구독 마감 시간</th>
                    <td>
                      <v-text-field
                        variant="plain"
                        v-model="this.lastDay[1]"
                        type="time"
                        readonly
                      ></v-text-field>
                    </td>
                  </tr>
                </template>
              </thead>
            </v-table>
            <div v-show="this.auctionData.kind !== '무료'">
              <v-table class="table-box">
                <thead>
                  <tr>
                    <th>현재 상태</th>
                    <td>
                      <v-text-field
                        variant="plain"
                        readonly
                        v-model="this.auctionData.status"
                      ></v-text-field>
                    </td>
                  </tr>

                  <tr v-show="this.auctionData.kind === '경매'">
                    <th>최고가</th>
                    <td>
                      <v-text-field
                        suffix="원"
                        readonly
                        variant="plain"
                        v-model="this.auctionData.payMax"
                      ></v-text-field>
                    </td>
                  </tr>
                </thead>
              </v-table>
            </div>
          </div>
          <v-btn
            v-if="this.auctionData.status !== '판매종료'"
            @click="editAuction"
            >수정</v-btn
          >

          <v-dialog v-else v-model="dialog" persistent width="500">
            <template v-slot:activator="{ props }">
              <v-btn variant="outlined" color="#FF9414" v-bind="props">
                구독으로 변경
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">구독 동영상으로 변경</span>
              </v-card-title>
              <v-card-text>
                <v-form v-model="valid" @submit.prevent="sendMessage">
                  <p>동영상 제공 기간</p>
                  <v-text-field
                    label="날짜"
                    v-model="Day"
                    type="date"
                    :rules="lastDaytRules"
                    required
                  ></v-text-field>
                  <v-text-field
                    label="시간"
                    v-model="Time"
                    type="time"
                    :rules="lastTimetRules"
                    required
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
                    <v-btn
                      type="submit"
                      color="blue-darken-1"
                      variant="text"
                      @click="dialog = false"
                    >
                      변경
                    </v-btn>
                  </v-card-actions>
                </v-form>
              </v-card-text>
            </v-card>
          </v-dialog>
        </div>
      </div>
      <!-- <v-btn @click="sendMessage">소켓으로 보내기</v-btn>
      <div v-for="(item, idx) in recvList" :key="idx">
        <h3>유저이름: {{ item.bidding }}</h3>
      </div> -->
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";

export default {
  components: { MypageNav },
  data() {
    return {
      auctionData: "",
      pay: "",
      category: "",
      startDay: "",
      lastDay: "",
      buyer: "",
      recvList: [],

      dialog: false,
      valid: false,
      Day: "",
      Time: "",
      wepsocket: "",

    };
  },
  mounted() {
    // 상세 내역 불러오기
    this.getAuction();
  },
  methods: {
    // 소켓 연결
    connect() {
      console.log("소켓연결");
      const serverURL = `${process.env.VUE_APP_API_URL}`;
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe(
            "/send",
            // `auctionDetail?ano=${this.$route.params.an}`,
            (res) => {
              console.log("구독으로 받은 메시지 입니다.", res.body);
              // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
              this.recvList.push(JSON.parse(res.body));
            }
          );
          console.log(this.recvList, "받아온데이터어어엉어어어");
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },
    // 소켓으로 데이터 보내기

    sendMessage() {
      console.log("버튼");
      this.send();
      this.pay;
      console.log(this.recvList, "받아온데이터어어엉어어어2");
    },

    send() {
      try {
        const res = this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/insertAttend/`,
          data: {
            ano: this.$route.params.ano,
            buyer: this.$store.state.sessionStorageData.mno,
            bidding: this.pay,
          },
        });
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          ano: this.$route.params.ano,
          buyer: 1,
          buyerNm: this.buyer,
          bidding: this.pay,
        };
        this.stompClient.send("/attend", JSON.stringify(msg), {});
      }
      console.log(this.recvList, "받아온데이터어어엉어어어3");
    },

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
        this.auctionData.vcate =
          this.auctionData.vcate === "animal"
            ? "동물"
            : this.auctionData.vcate === "chracter"
            ? "인물"
            : this.auctionData.vcate === "building"
            ? "건물"
            : this.auctionData.vcate === "plant"
            ? "식물"
            : "기타";
        this.startDay = this.auctionData.startDay.split(" ");
        this.lastDay = this.auctionData.lastDay.split(" ");
      } catch (error) {
        console.log(error);
      }
    },
    // 수정 페이지로 이동
    editAuction() {
      this.$router.push({
        name: "editauction",
        params: { ano: this.$route.params.ano },
      });
    },
    // 구독으로 넘길지 확인
    confirmToSub() {
      if (
        !confirm(
          "종료된 경매의 입찰을 취소하고 동영상을 구독으로 넘기시겠습니까?"
        )
      ) {
        console.log("아니요");
      } else {
        console.log("구독으로 넘기기");
        this.moveToSub();
      }
    },
    // 구독으로 넘기는 함수
    async moveToSub() {
      console.log("구독으로 넘겨오오오오오");
      const editData = {
        ano: this.auctionData.ano,
      };
      try {
        if (!this.valid) {
          console.log(this.valid);
          alert("제공 기간을 확인해주세요!");
          return;
        } else {
          const res = this.$axios({
            headers: {
              "Content-type": "application/json",
            },
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/auctionChange`,
            data: editData,
          });
          console.log(res);
        }
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

.page-box {
  /* width: 100%; */
  flex-direction: column;
  display: flex;
  padding-bottom: 10px;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  margin-bottom: 50px;
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

/* button {
  margin: 20px;
} */
</style>
