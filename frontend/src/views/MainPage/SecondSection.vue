<template>
  <section>
    <div id="f-div">
      <h2 style="font-size: 70px">마감이 임박한 동영상입니다.</h2>
      <p style="font-size: 30px">마감이 얼마 남지 않은 동영상입니다.</p>
      <p style="font-size: 30px">
        합리적인 가격으로 나만의 것으로 동영상을 소유해보세요!
      </p>
    </div>
    <div class="container">
      <div class="video-box">
        <video
          muted
          loop
          @mouseover="playVideo"
          @mouseleave="stopVideo"
          :src="`${videoSrc}/videoplay?ano=${deadlineData.ano}`"
        ></video>
      </div>
      <div>
        <h2>{{ deadlineData.title }}</h2>
        <p>{{ deadlineData.content }}</p>
        <p><span>마감일자 </span> {{ deadlineData.lastDay }}</p>
        <div style="align-items: center">
          <p
            style="
              width: 37%;
              padding: 2% 8%;
              display: flex;
              justify-content: center;
            "
          >
            시작가
          </p>
          <p>{{ $globalFuc(deadlineData.payStart) }} 원</p>
        </div>
        <div style="align-items: center">
          <p
            style="
              background-color: red;
              width: 37%;
              padding: 2% 8%;
              display: flex;
              justify-content: center;
            "
          >
            최고가
          </p>
          <p style="color: red">{{ $globalFuc(this.recvList.bidding) }} 원</p>
        </div>
        <div class="dday-box" style="justify-content: center">
          <div>
            <span>{{ this.days }}</span>
            <span>DAY</span>
          </div>
          <div>
            <span>{{ this.hours }}</span>
            <span>HOURS</span>
          </div>
          <div>
            <span>{{ this.minutes }}</span>
            <span>MINS</span>
          </div>
          <div>
            <span>{{ this.seconds }}</span>
            <span>SECS</span>
          </div>
        </div>
        <v-btn
          class="abtn"
          variant="flat"
          color="#5f9ea0"
          @click="modal = true && getInfo(deadlineData.ano)"
        >
          <p style="color: white; border-radius: 5px; padding: 4px 5px 0 5px">
            입찰
          </p>
        </v-btn>
      </div>
    </div>
    <v-dialog v-model="modal" width="auto">
      <v-card>
        <VideoDetail :videoData="videoData" />
        <v-card-actions>
          <v-btn block @click="closeModal">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </section>
</template>

<script>
import VideoDetail from "@/components/video/VideoDetail.vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
export default {
  components: {
    VideoDetail,
  },

  data() {
    return {
      recvList: "", //소켓에서 담긴 데이터
      videoData: "",
      deadlineData: "",
      ddayList: "",
      current: 0,
      previous: 0,
      play: false,
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
      modal: false,
      isScrolled: false,
    };
  },

  created() {
    this.getVideo();
  },
  mounted() {
    this.videoSrc = process.env.VUE_APP_API_URL;
    // 소켓 연결 시도
    setTimeout(() => {
      console.log(this.deadlineData.ano);
      this.connect(this.deadlineData.ano);
    }, 1000);
    this.getRemainingTime();
  },
  methods: {
    // 각 동영상 상세 정보 불러오기
    async getInfo(ano) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${ano}`,
        });
        this.videoData = res.data.data;
        console.log(this.videoData);
      } catch (e) {
        console.log(e);
      }
    },
    connect(ano) {
      console.log("소켓연결");
      const serverURL = `${process.env.VUE_APP_API_URL}`;
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      // 소켓 전역으로 설정
      this.$store.commit("storeSocket", this.stompClient);
      console.log(this.$store.state.socket, "소켓저자아아아앙");
      // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          console.log(this.videoData.ano, "소켓안에서설정");
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
          console.log(this.stompClient.subscriptions);
          console.log(this.recvList, "소켓 연결 성공하고 받아는 데이터");
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
      console.log("소켓종료?");
      this.stompClient.unsubscribe();
      console.log(this.stompClient.unsubscribe);
      console.log("소켓종료!!");
    },
    // 마우스오버시, 영상재생
    playVideo(e) {
      // console.log("마우스오버");
      e.target.play();
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      // console.log("마우스리브");
      e.target.pause();
      e.target.currentTime = 0;
    },

    // 동영상 불러오기
    async getVideo() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDeadline`,
        });
        this.deadlineData = res.data.data[0];
        console.log(res.data);
        this.dday = res.data.data[0].lastDay;
        // console.log(this.dday);
      } catch (e) {
        console.log(e);
      }
    },

    getRemainingTime() {
      // 0 이하면, 숫자 앞에 0을 붙이는 함수
      function format(item) {
        if (item < 10) {
          return (item = `0${item}`);
        }
        return item;
      }

      let x = setInterval(() => {
        const lastDayMs = new Date(this.dday).getTime();
        // console.log(lastDayMs);
        // 오늘 날짜
        const today = new Date().getTime();
        // console.log(today);
        // console.log(lastDayMs, today);

        // dday 산출 값
        const time = lastDayMs - today;
        // console.log(time, "dday의 ms");

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
    closeModal() {
      this.modal = false;
      this.closeSocket();
      this.getVideo();
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  /* height: 1440px; */
  display: flex;
  justify-content: center;
  margin-block: 15% 2%;
  flex-direction: column;
  & > div {
    & > p {
      letter-spacing: -1px;
    }
  }
}

#f-div {
  text-align: left;
  width: 100%;
  padding: 0 0 5% 10%;
}

.container {
  width: 100%;
  display: flex;
  text-align: end;
  padding: 0 10% 0;
  & > div:nth-child(2) {
    width: 60%;
    display: flex;
    flex-direction: column;
    align-items: center;
    & > h2 {
      margin: 5px;
    }
    & > p:nth-child(3) {
      margin: 5px;
      font-weight: bold;
      & span {
        color: #ff5e5e;
      }
    }

    & > div {
      display: flex;
      width: 80%;
      padding: 3%;
      // margin-bottom: 5%;
      justify-content: space-between;
      border-bottom: 1px solid #343434;

      & > p:nth-child(1) {
        background-color: #343434;
        color: white;
        border-radius: 5px;
        padding: 4px 5px 0 5px;
      }
      & > p:not(:first-of-type) {
        width: 100%;
      }
    }
  }
}

.dday-box {
  & > div {
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 1.5rem;
    font-weight: bold;

    @media (min-width: 500px) {
      & > span:nth-child(1) {
        width: 48px;
        height: 60px;
        margin: 0 1px;
        background-color: bisque;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 4px;
        font-size: 1.5rem;
      }
    }
    @media (min-width: 2500px) {
      & > span:nth-child(1) {
        width: 88px;
        height: 120px;
        margin: 0 1px;
        background-color: bisque;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 4px;
        font-size: 3rem;
      }
    }
    & > span:last-child {
      font-weight: 600;
      line-height: 1;
      margin-top: 0.25rem;
    }
  }
}

.video-box {
  width: 100%;
  & > video {
    width: inherit;
    height: inherit;
    object-fit: cover;
  }
}
button {
  width: 80%;
  margin: 5%;
  padding: 4%;
}

.abtn {
  display: flex;
}

@media (min-width: 500px) {
  p {
    font-size: 14px;
  }
  span {
    font-size: 14px;
  }
}

@media (min-width: 2500px) {
  h2 {
    font-size: 50px;
  }

  p {
    font-size: 30px;
  }

  span {
    font-size: 30px;
  }
}
</style>
