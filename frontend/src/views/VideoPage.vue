<template>
  <div id="root">
    <div id="page-root">
      <VideoCategory />
      <div>
        <div class="card-box">
          <v-card
            class="mx-auto"
            max-width="344"
            v-for="item in videoList"
            :key="item"
          >
            <div class="video-box">
              <video
                @mouseover="evnetAdd"
                :src="`${videoSrc}/videoplay?ano=${item.ano}`"
                controls
              ></video>
            </div>
            <!-- <v-img
              src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
              height="200px"
              cover
            ></v-img> -->

            <v-card-title> {{ item.title }}</v-card-title>
            <v-card-text>{{ item.content }}</v-card-text>
            <v-card-subtitle>
              <span>시작일자</span> {{ item.startDay }}
            </v-card-subtitle>
            <v-card-subtitle>
              <span>마감일자</span>{{ item.lastDay }}
            </v-card-subtitle>
            <div class="pay-box">
              <p>시작가</p>
              <p>{{ item.payStart }} 원</p>
              <p>yeonju05**</p>
            </div>
            <div class="pay-box">
              <p>최고가</p>
              <p>{{ item.payMax }} 원</p>
              <p>diswn1**</p>
            </div>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn variant="outlined" @click="showbox(item.ano)">
                입찰
              </v-btn>
            </v-card-actions>
            <template v-if="show && item.ano == this.num">
              <v-expand-transition>
                <div>
                  <v-divider></v-divider>
                  <v-card-text>
                    <form class="form-container">
                      <v-row>
                        <v-col cols="6">
                          <v-list-subheader>경매 참여 금액</v-list-subheader>
                        </v-col>
                        <v-col cols="10">
                          <v-text-field
                            variant="underlined"
                            suffix="원"
                          ></v-text-field>
                        </v-col>
                        <v-btn color="success"> 입찰하기 </v-btn>
                      </v-row>
                    </form>
                  </v-card-text>
                </div>
              </v-expand-transition>
            </template>
          </v-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VideoCategory from "@/components/VideoCategory.vue";
export default {
  components: { VideoCategory },

  data() {
    return {
      src: "",
      // videosrc: `${process.env.VUE_APP_API_URL}/videoplay?ano=${this.videoList.ano}`,
      show: false,
      mouseover: false,
      videoList: "",
      num: "",
      videoSrc: "",
    };
  },

  mounted() {
    this.video();
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    // 비디오 리스트 받아오기
    async video() {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll`,
        });
        this.videoList = res.data.data;
        console.log(this.videoList);
      } catch (e) {
        console.log(e);
      }
    },
    // evnetAdd() {
    //   this.mouseover = !this.mouseover;
    // },
    showbox(ano) {
      console.log(this.num);
      this.num = ano;
      this.show = !this.show;
      console.log(ano);
      console.log(this.num);
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
  flex-direction: column;
  align-items: center;
}
span {
  color: #ff5e5e;
  font-weight: bolder;
}
.pay-box {
  display: flex;
  justify-content: space-between;
  padding: 0 20px 5px 20px;
  & p {
    width: 90px;
    font-size: 0.9rem;
  }
  & > p:nth-child(1) {
    width: 60px;
    background-color: #343434;
    border-radius: 5px;
    color: white;
  }
}

.video-box {
  width: 344px;
  height: 200px;
}
video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card-box {
  width: 960px;
  display: flex;
  flex-wrap: wrap;
  padding: 30px;
  margin: 30px;
}
</style>
