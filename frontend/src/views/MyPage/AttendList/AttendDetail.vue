<template>
  <div class="container">
    <h2>경매 낙찰 내역</h2>
    <div>
      <div>
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
                    :src="`${videoSrc}/videoplay?ano=${this.attendData.ano}`"
                  ></video>
                  <v-btn
                    prepend-icon="mdi-arrow-down-bold-circle-outline"
                    color="orange"
                    variant="outlined"
                    @click="videoDownload"
                    >다운로드</v-btn
                  >
                </div>
              </td>
            </tr>
            <tr>
              <th>제목</th>
              <td colspan="3">
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.title"
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
                  v-model="this.attendData.content"
                  readonly
                ></v-textarea>
              </td>
            </tr>
            <tr>
              <th>카테고리</th>
              <td>
                <v-text-field
                  variant="plain"
                  :items="categorys"
                  v-model="this.attendData.vcate"
                  item-title="title"
                  item-value="value"
                  readonly
                ></v-text-field>
              </td>
              <th>경매 상태</th>
              <td>
                <v-text-field
                  v-model="this.attendData.status"
                  variant="plain"
                  readonly
                ></v-text-field>
              </td>
            </tr>
            <tr>
              <th>경매 시작가</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.payStart"
                  suffix="원"
                  readonly
                ></v-text-field>
              </td>
              <th>낙찰가</th>
              <td>
                <v-text-field
                  variant="plain"
                  v-model="this.attendData.payMax"
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
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: false,
      attendData: "",
      startDay: "",
      lastDay: "",
    };
  },

  mounted() {
    this.videoSrc = process.env.VUE_APP_API_URL;
    // 상세 내역 불러오기
    this.getAttend();
  },
  methods: {
    // 각 경매의 상세페이지 받아오기
    async getAttend() {
      console.log("경매조회");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionDetail?ano=${this.$route.params.ano}`,
        });
        console.log(res);
        this.attendData = res.data.data;
        this.startDay = this.attendData.startDay.split(" ");
        this.lastDay = this.attendData.lastDay.split(" ");
        this.attendData.payStart = this.$globalFuc(this.attendData.payStart);
        this.attendData.payMax = this.$globalFuc(this.attendData.payMax);
      } catch (error) {
        console.log(error);
      }
    },

    async videoDownload() {
      console.log("비디오 다운로드");
      console.log(this.attendData.ano);
      try {
        const res = await this.$axios({
          method: "GET",
          responseType: "blob", // 응답데이터 타입 정의
          url: `${process.env.VUE_APP_API_URL}/videoDownload/${this.attendData.ano}`,
        });
        console.log(res);
        const fileName = `video_${res.request.responseURL.substr(
          res.request.responseURL.lastIndexOf("/") + 1
        )}`;
        console.log(fileName);
        const blob = new Blob([res.data]);
        console.log(blob);

        const fileObjectUrl = window.URL.createObjectURL(blob);
        console.log(fileObjectUrl);

        const fileLink = document.createElement("a");
        fileLink.href = fileObjectUrl;

        fileLink.setAttribute("download", `${fileName}.mp4`);
        document.body.appendChild(fileLink);

        fileLink.click();
      } catch (error) {
        console.log(error);
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
    display: flex;
    padding-bottom: 10px;
  }
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
  width: 520px;
  height: 430px;
  margin: 10px;
  & > video {
    width: inherit;
    height: 360px;
    object-fit: cover;
  }
}

button {
  margin-top: 20px;
}
</style>
