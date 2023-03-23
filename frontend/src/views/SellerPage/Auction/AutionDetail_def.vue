<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="mypage">
        <h2>경매 상세페이지</h2>
        <div class="form-box">
          <v-form>
            <v-text-field
              label="제목"
              v-model="this.auctionData.title"
              readonly
            ></v-text-field>
            <v-textarea
              label="내용"
              v-model="this.auctionData.content"
              readonly
            ></v-textarea>
            <v-select
              label="카테고리"
              v-model="this.auctionData.vcate"
              readonly
            ></v-select>

            <v-text-field
              label="경매 시작가"
              v-model="this.auctionData.payStart"
              readonly
              suffix="원"
            ></v-text-field>
            <v-text-field
              label="시작 날짜"
              v-model="this.startDay[0]"
              readonly
              type="date"
            ></v-text-field>
            <v-text-field
              label="시작 시간"
              v-model="this.startDay[1]"
              readonly
              type="time"
            ></v-text-field>
            <v-text-field
              label="마감 날짜"
              v-model="this.lastDay[0]"
              readonly
              type="date"
            ></v-text-field>
            <v-text-field
              label="마감 시간"
              v-model="this.lastDay[1]"
              readonly
              type="time"
            ></v-text-field>
            <!-- 
                경매전 : 경매 시작가, 
                경매중 : 경매 시작가, 최고가, 
                경매완료 : 경매 시작가, 최고가, 낙찰자아이디(뒤에 두자리별표)
            -->
            <v-text-field
              label="마감 시간"
              v-model="this.lastDay[1]"
              readonly
              type="time"
            ></v-text-field>
            <v-btn block color="success" variant="elevated" class="mt-2"
              >경매 수정</v-btn
            >
          </v-form>
          <v-btn @click="sendMessage"> 버튼</v-btn>
          <v-text-field v-model="pay"></v-text-field>
          <v-text-field v-model="name"></v-text-field>
          <v-text-field v-model="number"></v-text-field>
          <div v-for="(item, idx) in recvList" :key="idx">
            <h3>유저이름: {{ item.pay }}</h3>
            <h3>내용: {{ item.number }}</h3>
            <h3>내용: {{ item.name }}</h3>
          </div>
        </div>
      </div>
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
      startDay: "",
      lastDay: "",
      // 웹소켓을 이용해서 받은 데이터를 담을 곳
      recvList: [],
      wepsocket: "",
      pay: "",
      name: "",
      number: "",
    };
  },

  created() {
    // 소켓 연결 시도
    this.connect();
    // this.socket();
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
            `auctionDetail?ano=${this.$route.params.ano}`,
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
      console.log(this.recvList, "받아온데이터어어엉어어어2");
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          ano: this.$route.params.ano,
          buyer: 1,
          buyerNm: "name",
          bidding: this.pay,
        };
        this.stompClient.send(
          `auctionDetail?ano=${this.$route.params.ano}`,
          JSON.stringify(msg),
          {}
        );
      }
      console.log(this.recvList, "받아온데이터어어엉어어어3");
    },
    //소켓연결
    // socket() {
    // this.wepsocket = new WebSocket(
    //   `ws//localhost:80/auctionDetail?ano=${this.$route.params.ano}`
    // );
    //     const ws = new WebSocket(
    //       `ws//localhost:80/auctionDetail?ano=${this.$route.params.ano}`,
    // "protocolOne"
    //     );
    // console.log(this.wepsocket);
    // ws.onerror = (error) => console.log(error);
    // ws.onclose = (event) => console.log(event);
    //   console.log(this.ws);
    //   ws.onmessage = function() {
    //     const data = JSON.parse(msg.data);
    //     let css;
    //     if (data.mid === mid.value) {
    //         css = 'class=me';
    //     } else {
    //         css = 'class=other';
    //     }
    //     const item = `<div ${css} >
    //                 <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
    //                   <span>${data.msg}</span>
    // 				</div>`;
    //     talk.innerHTML += item;
    //     // 2022.10.26[프뚜]: 스크롤바 하단으로 이동
    //     talk.scrollTop=talk.scrollHeight;
    // }
    // },

    // `ws//localhost:8888/auctiondetail/1`

    // 각 경매의 상세페이지 받아오기
    async getAuction() {
      console.log("경매조회");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/auctionDetail?ano=${this.$route.params.ano}`,
        });
        console.log(res);
        this.auctionData = res.data.data;
        this.startDay = this.auctionData.startDay.split(" ");
        this.lastDay = this.auctionData.lastDay.split(" ");
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
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.mypage {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  h2 {
    margin-bottom: 20px;
  }
}
.form-box {
  width: 500px;
}
</style>
