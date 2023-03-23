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
      auctionData: "",
      startDay: "",
      lastDay: "",
    };
  },

  created() {},
  mounted() {
    this.getAuction();
  },
  methods: {
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
