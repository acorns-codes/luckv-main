<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="container">
        <h2>경매 수정</h2>
        <div>
          <div class="page-box">
            <v-form v-model="valid" @submit.prevent="editAuction">
              <v-table class="table-box">
                <thead>
                  <tr>
                    <th>제목</th>
                    <td>
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
                    <td>
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
                  </tr>
                  <!-- <tr>
                    <th>동영상</th>
                    <td>
                      <v-file-input
                        variant="plain"
                        prepend-icon="mdi-video"
                        v-model="video"
                        :rules="videoRules"
                        required
                      ></v-file-input>
                    </td>
                  </tr> -->

                  <tr>
                    <th>경매 시작가</th>
                    <td>
                      <v-text-field
                        variant="plain"
                        v-model="this.auctionData.payStart"
                        suffix="원"
                        :rules="payStartRules"
                        required
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
                        :rules="StartDaytRules"
                        required
                        readonly
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
                        :rules="startTimetRules"
                        required
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
                        :rules="lastDaytRules"
                        required
                        readonly
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
                        :rules="lastTimetRules"
                        required
                        readonly
                      ></v-text-field>
                    </td>
                  </tr>
                </thead>
              </v-table>

              <v-btn
                type="submit"
                block
                color="success"
                variant="elevated"
                class="mt-2"
                >수정</v-btn
              >
            </v-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";

export default {
  components: { MypageNav },
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
      videoRules: [(v) => !!v || "비디오 등록은 필수 사항입니다."],
      payStartRules: [(v) => !!v || "경매 시작가는 필수 입력 사항입니다."],
      StartDaytRules: [(v) => !!v || "시작 날짜는 필수 입력 사항입니다."],
      startTimetRules: [(v) => !!v || "시작 시간는 필수 입력 사항입니다."],
      lastDaytRules: [(v) => !!v || "마감 날짜는 필수 입력 사항입니다."],
      lastTimetRules: [(v) => !!v || "마감 시간는 필수 입력 사항입니다."],
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
    // 상세 내역 불러오기
    this.getAuction();
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
      } catch (error) {
        console.log(error);
      }
    },
    async editAuction() {
      console.log("경매내역 수정");
      try {
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/auctionUpdate`,
          data: {
            title: this.auctionData.title,
            content: this.auctionData.content,
            vcate: this.auctionData.vcate,
            seller: this.$store.state.sessionData.mno,
            ano: this.$route.params.ano,
          },
        });
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
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
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    /* flex-direction: column; */
    display: flex;
    /* align-items: center; */

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
  width: 400px;
  height: 300px;
  background-color: antiquewhite;
  margin: 0 auto;
  margin-bottom: 30px;
}

button {
  margin-top: 20px;
}
</style>
