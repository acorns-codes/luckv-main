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
              </thead>
            </v-table>
            <div>
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
                  <tr>
                    <th>낙찰자</th>
                    <td>
                      <v-text-field
                        variant="plain"
                        readonly
                        v-model="this.auctionData.buyerNm"
                      ></v-text-field>
                    </td>
                  </tr>
                  <tr>
                    <th>최고가</th>
                    <td>
                      <v-text-field
                        suffix="원"
                        readonly
                        variant="plain"
                        v-model="pay"
                      ></v-text-field>
                    </td>
                  </tr>
                </thead>
              </v-table>
            </div>
          </div>
          <v-btn @click="editAuction">수정</v-btn>
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
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
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
      wepsocket: "",
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

  created() {
    // 소켓 연결 시도
    this.connect();
  },
  mounted() {
    // 상세 내역 불러오기
    this.getAuction();
  },
  methods: {
    // 소켓 연결
    connect() {
      console.log("소켓연결");
      const serverURL = `http://localhost:80`;
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
        this.startDay = this.auctionData.startDay.split(" ");
        this.lastDay = this.auctionData.lastDay.split(" ");
      } catch (error) {
        console.log(error);
      }
    },
    editAuction() {
      this.$router.push({
        name: "editauction",
        params: { ano: this.$route.params.ano },
      });
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
  /* align-items: center; */
  padding-bottom: 10px;
}
.container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
  padding-top: 110px;
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
  margin: 20px;
}
</style>
