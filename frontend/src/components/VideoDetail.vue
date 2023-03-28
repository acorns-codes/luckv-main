<template>
  <div id="root">
    <div id="container">
      <seciton>
        <div class="video-box">
          <video
            :src="`${videoSrc}/videoplay?ano=${this.videoData.ano}`"
          ></video>
        </div>
      </seciton>

      <seciton class="info-box">
        <div>
          <h2>{{ this.videoData.title }}</h2>
          <p>{{ this.videoData.content }}</p>
        </div>
        <hr />
        <div>
          <p>시작가</p>
          <p>{{ this.videoData.payStart }} 원</p>
        </div>
        <div>
          <p>최고가</p>
          <p>{{ this.videoData.payMax }} 원</p>
        </div>
        <div>
          <p>경매기간</p>
          <p>{{ this.videoData.startDay }} ~ {{ this.videoData.lastDay }}</p>
        </div>
        <hr />
        <div>
          <p>남은시간</p>
          <p>00일00시00분00초</p>
          <p>{{ this.seconds }}</p>
        </div>
        <v-btn variant="elevated" color="#FF9414" size="large">입찰하기</v-btn>
      </seciton>
    </div>
  </div>
</template>

<script>
import getRemainingTime from "@/plugins/function/functions.js";
export default {
  data() {
    return {
      videoData: "",
      dday: "",
      ddayData: "",
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
    };
  },

  computed() {},
  created() {
    this.getInfo();
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  mounted() {
    // console.log(this.dday);
    // setInterval(() => getRemainingTime(this.dday, 2000));
    setInterval(() => getRemainingTime("2023-04-01 16:00", 1000));
    this.getRemainingTime();
  },
  methods: {
    /// 날짜 ////
    getRemainingTime() {
      // 마감날짜
      const lastDayMs = new Date(this.dday).getTime();
      // console.log(lastDayMs);
      // 오늘 날짜
      const today = new Date().getTime();
      // console.log(today);
      // console.log(lastDayMs, today);

      // dday 산출 값
      const time = lastDayMs - today;
      // console.log(time, "dday의 ms");
      let x = setInterval(() => {
        // dday 산출을 위해 필요한 값
        const oneDay = 24 * 60 * 60 * 1000;
        const oneHour = 60 * 60 * 1000;
        const oneMinute = 60 * 1000;
        let days = time / oneDay;
        days = Math.floor(days);
        let hours = Math.floor((time % oneDay) / oneHour);
        let minutes = Math.floor((time % oneHour) / oneMinute);
        let seconds = Math.floor((time % oneMinute) / 1000);

        // console.log(seconds);
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        // const values = [days, hours, minutes, seconds];
        // console.log(values);
        // this.ddayList = values;

        // console.log(values);
        // return values;
      }, 1000);
      console.log(x);
    },

    ////
    // 동영상정보 불러오기
    async getInfo() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${this.$route.params.ano}`,
        });
        this.videoData = res.data.data;
        console.log(this.videoData);
        this.dday = res.data.data.lastDay;
        console.log(this.dday);
      } catch (e) {
        console.log(e);
      }
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
}

.table-box {
  width: 700px;
  margin: 10px;
  border-top: 1px solid #343434;
  th {
    width: 70px;
  }
  th,
  td {
    border-bottom: 1px solid #eee;
    padding: 10px 10px;
    text-align: left;
  }
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
  display: flex;
  flex-direction: column;
  align-items: flex-start;

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
hr {
  width: inherit;
  border: 0.5px solid #eee;
  margin: 10px 0;
}
</style>
