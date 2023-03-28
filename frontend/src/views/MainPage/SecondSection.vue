<template>
  <section>
    <div>
      <h2>마감이 임박한 동영상입니다.</h2>
      <p>마감이 얼마 남지 않은 동영상입니다.</p>
      <p>합리적인 가격으로 나만의 것으로 동영상을 소유해보세요!</p>
      <div class="container">
        <div>
          <h2>{{ deadlineData.title }}</h2>
          <p>{{ deadlineData.content }}</p>
          <p><span>마감일자 </span> {{ deadlineData.lastDay }}</p>
          <div>
            <p>시작가</p>
            <p>{{ deadlineData.payStart }} 원</p>
          </div>
          <div>
            <p>최고가</p>
            <p>{{ deadlineData.payMax }} 원</p>
          </div>
          <div class="dday-box">
            <div>
              <span>01</span>
              <span>DAY</span>
            </div>
            <div>
              <span>23</span>
              <span>HOURS</span>
            </div>
            <div>
              <span>50</span>
              <span>MINS</span>
            </div>
            <div>
              <span>30</span>
              <span>SECS</span>
            </div>
          </div>
          <v-btn variant="flat" color="#FF9414" @click="goVideo"> 입찰 </v-btn>
        </div>
        <div class="video-box">
          <video
            ref="video"
            muted
            loop
            @mouseover="playVideo"
            @mouseleave="stopVideo"
            :src="`${videoSrc}/videoplay?ano=${deadlineData.ano}`"
          ></video>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
// import priceToString from "@/plugins/function/funciton.js";

export default {
  data() {
    return {
      deadlineData: "",
      ddayList: "",
      current: 0,
      previous: 0,
      play: false,
    };
  },

  created() {
    this.getVideo();
  },
  mounted() {
    this.videoSrc = process.env.VUE_APP_API_URL;
    // this.dday = this.deadlineData.lastDay;
    // console.log(this.deadlineData);
    // console.log(this.dday);
    setInterval(this.getRemainingTime(this.dday), 1000);
    // console.log(this.ddayList);
    console.log(this.$refs.video);
  },
  methods: {
    // 마우스오버시, 영상재생
    playVideo(e) {
      // console.log("마우스오버");
      e.target.play();
    },
    // 마우스리브시, 영상 일시정지
    stopVideo(e) {
      console.log("마우스리브");
      e.target.pause();
      e.target.currentTime = 0;
    },
    // day 구하기
    getRemainingTime(lastday) {
      // 마감날짜
      const lastDayMs = new Date(lastday).getTime();
      console.log(lastDayMs);
      // 오늘 날짜
      const today = new Date().getTime();
      console.log(today);
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

      const values = [days, hours, minutes, seconds];
      // console.log(values);
      // this.ddayList = values;
      // console.log(values);
      console.log(values);
      return values;
    },
    // 동영상 불러오기
    async getVideo() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll`,
        });

        this.deadlineData = res.data.data[0];
        this.dday = res.data.data[0].lastDay;
        console.log(this.dday);
      } catch (e) {
        console.log(e);
      }
      try {
        setInterval(this.getRemainingTime(this.dday), 1000);
      } catch (e) {
        console.log(e);
      }
    },

    // getRemainingTime() {
    //   // 마감날짜
    //   const lastDayMs = new Date(this.dday).getTime();
    //   // 오늘 날짜
    //   const today = new Date().getTime();

    //   // console.log(lastDayMs, today);

    //   // dday 산출 값
    //   const time = lastDayMs - today;
    //   // console.log(time, "dday의 ms");

    //   // dday 산출을 위해 필요한 값
    //   const oneDay = 24 * 60 * 60 * 1000;
    //   const oneHour = 60 * 60 * 1000;
    //   const oneMinute = 60 * 1000;
    //   let days = time / oneDay;
    //   days = Math.floor(days);
    //   let hours = Math.floor((time % oneDay) / oneHour);
    //   let minutes = Math.floor((time % oneHour) / oneMinute);
    //   let seconds = Math.floor((time % oneMinute) / 1000);

    //   const values = [days, hours, minutes, seconds];
    //   // console.log(values);
    //   this.ddayList = values;
    //   console.log(values);
    // },
    // let countdown = setInterval(getRemainingTime, 1000);
    // getRemainingTime();
    // console.log(countdown);
    goVideo() {
      var nWidth = "1300";

      var nHeight = "800";

      var xPos = document.body.clientWidth / 2 - nWidth / 2;

      xPos += window.screenLeft; //듀얼 모니터

      var yPos = screen.availHeight / 2 - nHeight / 2;
      let url = `/videodetail/${this.deadlineData.ano}`;
      // window.open(url, 500, 300);
      window.open(
        url,
        "popOpen",
        "width=" +
          nWidth +
          ",height=" +
          nHeight +
          ", left=" +
          xPos +
          ", top=" +
          yPos +
          ", toolbars=no, resizable=no, scrollbars=no"
      );
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  height: 630px;
  display: flex;
  justify-content: center;
  & > div {
    width: 1240px;
    padding: 90px;
    display: flex;
    flex-direction: column;
    text-align: end;
    & > p {
      letter-spacing: -1px;
    }
  }
}

.container {
  display: flex;
  justify-content: flex-end;
  & > div:nth-child(1) {
    width: 725px;
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
      width: 50%;
      padding: 5px;
      margin-bottom: 5px;
      justify-content: space-between;
      border-bottom: 1px solid #343434;

      & > p:nth-child(1) {
        background-color: #343434;
        color: white;
        border-radius: 5px;
        padding: 4px 5px 0 5px;
        font-size: 0.8rem;
      }
      & > p:not(:first-of-type) {
        width: 100px;
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
    margin: 0 -4px 0.75rem;
    font-size: 1.5rem;
    font-weight: bold;

    & > span:nth-child(1) {
      width: 48px;
      height: 60px;
      margin: 0 1px;
      background-color: bisque;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 4px;
    }
    & > span:last-child {
      font-size: 0.75rem;
      font-weight: 600;
      line-height: 1;
      margin-top: 0.25rem;
    }
  }
}

.video-box {
  width: 420px;
  height: 360px;
  & > video {
    width: inherit;
    height: inherit;
    object-fit: cover;
  }
}
button {
  width: 200px;
  margin: 15px;
}
</style>
