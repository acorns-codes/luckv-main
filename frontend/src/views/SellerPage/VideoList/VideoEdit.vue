<template>
  <div class="container">
    <h2>경매 수정</h2>
    <div>
      <div class="page-box">
        <v-form v-model="valid" @submit.prevent="editAuction">
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
                      :src="`${videoSrc}/videoplay?ano=${this.auctionData.ano}`"
                    ></video>
                  </div>
                </td>
              </tr>
              <tr>
                <th>제목</th>
                <td colspan="3">
                  <v-text-field
                    variant="plain"
                    v-model="this.auctionData.title"
                    :rules="titleRules"
                    required
                  ></v-text-field>
                </td>
              </tr>
              <tr class="content">
                <th>내용</th>
                <td colspan="3">
                  <v-textarea
                    rows="10"
                    variant="plain"
                    v-model="this.auctionData.content"
                    :rules="contentRules"
                    required
                  ></v-textarea>
                </td>
              </tr>
              <tr>
                <th>카테고리</th>
                <td>
                  <v-select
                    variant="plain"
                    required
                    :items="categorys"
                    v-model="this.auctionData.vcate"
                    item-title="title"
                    item-value="value"
                  ></v-select>
                </td>
                <th>경매 상태</th>
                <td>
                  <v-text-field
                    v-model="this.auctionData.status"
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
                    v-model="this.auctionData.payStart"
                    suffix="원"
                    readonly
                  ></v-text-field>
                </td>
                <th>최고가</th>
                <td>
                  <v-text-field
                    variant="plain"
                    v-model="this.auctionData.payMax"
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
              <tr></tr>
            </thead>
          </v-table>
          <v-btn type="submit" color="success" variant="elevated" class="mt-2"
            >수정</v-btn
          >
        </v-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: false,
      auctionData: "",
      startDay: "",
      lastDay: "",
      titleRules: [
        (v) => !!v || "제목은 필수 입력사항입니다.",
        // (v) =>
        //   /^[A-Za-z0-9]{2,20}$/g.test(v) ||
        //   "제목은 2자 이상 20자 이하로 작성해주세요.",
      ],
      contentRules: [
        (v) => !!v || "내용은 필수 입력사항입니다.",
        // (v) =>
        //   /^[a-z0-9@.]{2,150}$/g.test(v) ||
        //   "내용은 2자 이상 150자 이하로 작성해주세요.",
      ],
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

  mounted() {
    this.videoSrc = process.env.VUE_APP_API_URL;
    // 상세 내역 불러오기
    this.getAuction();
    // console.log(this.$store.state.sessionStorageData);
  },
  methods: {
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
        this.auctionData.payStart = this.$globalFuc(this.auctionData.payStart);
        this.auctionData.payMax = this.$globalFuc(this.auctionData.payMax);
      } catch (error) {
        console.log(error);
      }
    },
    async editAuction() {
      console.log("경매내역 수정");
      const editdata = {
        title: this.auctionData.title,
        content: this.auctionData.content,
        vcate: this.auctionData.vcate,
        seller: this.$store.state.sessionStorageData.mno,
        ano: this.$route.params.ano,
      };
      try {
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/auctionUpdate`,
          data: editdata,
        });
        console.log(editdata);
        console.log(res);
        if (res.data.data) {
          alert("게시물 수정이 완료되었습니다!");
          this.$router.push({
            name: "auctionDetail",
            params: { page: 1 },
          });
        }
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
  width: 520px;
  height: 360px;
  margin: 10px;
  & > video {
    width: inherit;
    height: inherit;
    object-fit: cover;
  }
}

button {
  margin-top: 20px;
}
</style>
