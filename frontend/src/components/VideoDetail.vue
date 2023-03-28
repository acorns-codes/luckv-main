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
        <div>
          <p>남은시간</p>
          <p>00일00시00분00초</p>
        </div>
        <v-btn variant="elevated" color="success" size="large">입찰하기</v-btn>
      </seciton>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      videoData: "",
    };
  },
  mounted() {
    this.getInfo();
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
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
    margin-bottom: 30px;
    border-bottom: 1px solid #eee;
  }
  & > div {
    display: flex;
    & > p:nth-child(1) {
      width: 80px;
      text-align: left;
    }
  }
}
</style>
