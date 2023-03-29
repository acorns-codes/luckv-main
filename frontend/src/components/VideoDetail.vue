<template>
  <div id="root">
    <div id="container">
      <section>
        <div class="video-box">
          <video
            :src="`${videoSrc}/videoplay?ano=${this.videoData.ano}`"
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
            <p>{{ this.videoData.payStart }} 원</p>
          </div>
          <div>
            <p>최고가</p>
            <p style="color: red; font-weight: bo">
              {{ this.videoData.payMax }} 원
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
        </template>

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
              <v-form v-model="valid" @submit.prevent="bidding">
                <p>경매 입찰 금액</p>
                <v-text-field
                  suffix="원"
                  required
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
      </section>
    </div>
  </div>
</template>

<script>
// import getRemainingTime from "@/plugins/function/functions.js";
export default {
  props: ["videoData"],
  data() {
    return {
      state: "ins",
      dialog: false,
      valid: false,
      price: "",
      // videoData: "",
      dday: "",
      ddayData: "",
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
      priceRule: [
        (v) => /^[0-9]+/g.test(v) || "숫자만 입력해주세요",
        (v) =>
          this.state === "ins" ? !!v || "금액은 필수 입력사항입니다." : true,
        (v) =>
          this.state === "ins"
            ? this.videoData.payStart < v ||
              "시작가보다 높게 입찰금액을 입력하셔야합니다."
            : true,
        (v) =>
          this.state === "ins"
            ? this.videoData.payMax < v ||
              "최고가보다 높게 입찰금액을 입력하셔야합니다."
            : true,
      ],
    };
  },
  created() {
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  mounted() {
    this.getRemainingTime();
  },
  methods: {
    // 입찰하기
    async bidding() {
      try {
        const actionData = {
          ano: this.videoData.ano,
          buyer: this.$store.state.sessionStorageData.mno,
          bidding: this.price,
        };

        console.log(actionData);
        if (!this.valid) {
          console.log(this.valid);
          alert("입찰 금액을 확인해주세요!");
          return;
        } else {
          // 입찰 데이터 보내기
          const res = await this.$axios({
            headers: {
              "Content-type": "application/json",
            },
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/insertAttend`,
            data: actionData,
          });
          if (res.data.data) {
            alert("성공적으로 입찰이 등록되었습니다!");
            window.location.reload();
          } else {
            alert("입찰에 실패하였습니다!");
          }

          // this.$router.push({
          //   path: "/",
          // });
        }
        // console.log(this.valid);
      } catch (error) {
        console.log(error);
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
  height: 320px;
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
}
</style>
