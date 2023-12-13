<template>
  <div id="root">
    <div id="container">
      <section>
        <div class="video-box">
          <video
            :src="`${this.videoSrc}/video/play?ano=${this.videoData.ano}`"
            controls
          ></video>
        </div>
      </section>
      <section class="info-box">
        <div>
          <h2>{{ this.videoData.title }}</h2>
          <p>{{ this.videoData.content }}</p>
        </div>
        <hr />
        <template v-if="this.videoData.kind === '경매'">
          <div>
            <p>시작가</p>
            <p>{{ $globalFuc(this.videoData.payStart) }} 원</p>
          </div>
          <div>
            <p style="color: red">최고가</p>
            <p style="color: red; font-weight: bold">
              {{ $globalFuc(this.recvList.bidding) }}원
            </p>
          </div>
          <div>
            <p>경매기간</p>
            <p>{{ this.videoData.startDay }} ~ {{ this.videoData.lastDay }}</p>
          </div>
          <hr />
          <div class="last-box">
            <p>남은시간</p>
            <p>
              {{ this.days }}일 {{ this.hours }}시 {{ this.minutes }}분
              {{ this.seconds }}초
            </p>
          </div>
          <div></div>
          <v-dialog v-model="dialog" persistent width="500">
            <template v-slot:activator="{ props }">
              <v-btn color="#FF9414" size="x-large" v-bind="props">
                입찰하기
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">입찰하기</span>
              </v-card-title>
              <v-card-text>
                <v-form v-model="valid" @submit.prevent="sendMessage">
                  <p>경매 입찰 금액</p>
                  <v-text-field
                    suffix="원"
                    required
                    :placeholder="`현재 최고가 : ${this.recvList.bidding}`"
                    :rules="priceRule"
                    v-model="price"
                  ></v-text-field>
                  <small>*현재 최고가보다 높게 입찰하여야 합니다</small>
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
                      입찰
                    </v-btn>
                  </v-card-actions>
                </v-form>
              </v-card-text>
            </v-card>
          </v-dialog>
        </template>
        <template v-else-if="this.videoData.kind == '무료'">
          <div class="last-box">
            <h3>안내사항</h3>
            <p>모든 회원에게 무료로 제공되는 동영상입니다.</p>
            <p>사용 후 출처를 남겨주시면 감사하겠습니다.</p>
          </div>
          <v-btn color="#FF9414" size="x-large" @click="videoDownload"
            >다운로드
          </v-btn>
        </template>
        <template v-else>
          <div class="last-box">
            <h3>안내사항</h3>
            <p>구독을 신청한 회원님들께만 제공되는 동영상입니다.</p>
            <p>
              목록에 있는 동영상을 자유롭게 사용하시고, 출처를 표기해주세요.
            </p>
          </div>
          <v-btn color="#FF9414" size="x-large" @click="videoDownload"
            >다운로드</v-btn
          >
        </template>
      </section>
    </div>
  </div>
</template>

<script>
import { apiAddAttend } from "@/api/attend";
// import getRemainingTime from "@/plugins/function/functions.js";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
export default {
  props: ["videoData"],
  data() {
    return {
      //소켓에서 담긴 데이터
      recvList: "",
      dialog: false,
      valid: false,
      price: 0,
      dday: "",
      ddayData: "",
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
      priceRule: [
        (v) => (v == null || v == "" ? "금액은 필수 입력사항입니다." : true),
        (v) => (/^[0-9]+/g.test(v) ? true : "숫자만입력해주세요."),
        (v) =>
          this.videoData.payStart > v
            ? "시작가보다 높게 입찰금액을 입력하셔야합니다."
            : true,
        (v) =>
          this.recvList.bidding > v
            ? "최고가보다 높게 입찰금액을 입력하셔야합니다."
            : true,
      ],
    };
  },
  computed: {
    videoSrc() {
      return this.$store.state.videoSrc;
    },
  },
  created() {
    // 소켓 연결 시도
    this.connect();
  },
  mounted() {
    // 소켓 연결 시도
    setTimeout(() => {
      this.connect(this.videoData.ano);
    }, 1000);
    // dday 게산 함수
    this.getRemainingTime();
  },
  methods: {
    // 소켓 연결
    connect(ano) {
      console.log("소켓연결");
      const serverURL = `${process.env.VUE_APP_API_URL}`;
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe(
            `/send/${ano}`,
            (res) => {
              console.log("구독으로 받은 메시지 입니다.", res.body);
              // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
              // res.body
              // 처음엔 여기서 아무거도 안받아졌는데, 입찰 버튼 눌러서 입찰하게되면
              // res.body안에
              this.recvList = JSON.parse(res.body);
            },
            { ano: ano }
          );
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },

    // 소켓 종료
    closeSocket() {
      this.stompClient.unsubscribe();
      console.log("소켓종료!!");
    },
    // 소켓으로 데이터 보내기
    async sendMessage() {
      if (!this.price) {
        return alert("입찰 금액을 입력해주세요");
      } else if (!this.valid) {
        return alert("입찰가를 확인해주세요!");
      } else if (this.$store.state.sessionStorageData.auth !== "B") {
        alert("구매자회원만 입찰에 참여할 수 있습니다!");
        return (this.price = 0);
      }
      // 입찰 금액 보내기
      await this.bidding();
    },

    // 입찰금액 보내기
    async bidding() {
      const req = {
        ano: this.videoData.ano,
        buyer: this.$store.state.sessionStorageData.mno,
        bidding: this.price,
      };
      try {
        const res = await apiAddAttend(req);
        if (res) {
          if (this.stompClient && this.stompClient.connected) {
            const msg = {
              ano: this.videoData.ano,
              buyer: this.$store.state.sessionStorageData.mno,
              buyerNm: this.$store.state.sessionStorageData.name,
              bidding: this.price,
            };
            this.stompClient.send(
              `/attend/${this.videoData.ano}`,
              JSON.stringify(msg),
              {}
            );
          }
          alert("입찰에 성공하였습니다!");
        }
      } catch (error) {
        console.error(error);
      }
    },

    // video 다운로드
    async videoDownload() {
      if (this.$store.state.subAuth == "N" && this.videoData.kind == "구독") {
        return alert("구독회원만 다운받을 수 있습니다.");
      }
      try {
        const res = await this.$axios({
          method: "GET",
          responseType: "blob", // 응답데이터 타입 정의
          url: `${process.env.VUE_APP_API_URL}/video/download/${this.videoData.ano}`,
        });
        const fileName = `video_${res.request.responseURL.substr(
          res.request.responseURL.lastIndexOf("/") + 1
        )}`;
        const blob = new Blob([res.data]);
        const fileObjectUrl = window.URL.createObjectURL(blob);
        const fileLink = document.createElement("a");
        fileLink.href = fileObjectUrl;
        fileLink.setAttribute("download", `${fileName}.mp4`);
        document.body.appendChild(fileLink);
        fileLink.click();
      } catch (error) {
        console.error(error);
      }
    },

    /// 날짜 ////
    getRemainingTime() {
      // 0 이하면, 숫자 앞에 0을 붙이는 함수
      function format(item) {
        if (item < 10) {
          return (item = `0${item}`);
        }
        return item;
      }
      let x = setInterval(() => {
        const lastDayMs = new Date(this.videoData.lastDay).getTime();
        // 오늘 날짜
        const today = new Date().getTime();
        // dday 산출 값
        const time = lastDayMs - today;
        // dday 산출을 위해 필요한 값
        const oneDay = 24 * 60 * 60 * 1000;
        const oneHour = 60 * 60 * 1000;
        const oneMinute = 60 * 1000;
        let days = time / oneDay;
        days = Math.floor(days);
        let hours = Math.floor((time % oneDay) / oneHour);
        let minutes = Math.floor((time % oneHour) / oneMinute);
        let seconds = Math.floor((time % oneMinute) / 1000);
        this.days = format(days);
        this.hours = format(hours);
        this.minutes = format(minutes);
        this.seconds = format(seconds);
      }, 1000);
      console.log(x);
    },
  },
};
</script>
<style lang="scss" scoped>
#root {
  width: 100%;
  display: flex;
  justify-content: center;
}
#container {
  width: 1080px;
  padding: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.video-box {
  width: 480px;
  height: 320px;
  margin: 10px;
  cursor: pointer;

  & > video {
    width: inherit;
    height: inherit;
    object-fit: cover;
    border-radius: 10px;
  }
}

.info-box {
  width: 500px;
  height: 250px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;

  & > div:nth-child(1) {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  & > div {
    display: flex;
    & > p:nth-child(1) {
      width: 80px;
      text-align: left;
    }
  }
}
.last-box {
  display: flex;
  flex-direction: column;
}
hr {
  width: inherit;
  border: 0.5px solid #eee;
  margin: 10px 0;
}
button {
  margin-top: 10px;
  width: inherit;
}
</style>
