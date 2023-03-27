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
                src="http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/videoplay?ano=1"
                controls
              ></video>
            </div>

            <v-card-title> {{ item.title }}</v-card-title>
            <v-card-text>{{ item.content }}</v-card-text>
            <v-card-subtitle>
              <span>시작일자</span> {{ item.startDay }}
            </v-card-subtitle>
            <v-card-subtitle>
              <span>마감일자</span> {{ item.lastDay }}
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
              <v-btn variant="outlined" @click="show = !show"> 입찰 </v-btn>
            </v-card-actions>

            <v-expand-transition>
              <div v-show="show">
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
      show: false,
      mouseover: false,
      videoList: "",
    };
  },
  mounted() {
    this.getVideo();
  },
  methods: {
    async getVideo() {
      console.log("비디오받아오기");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/auctionAll?vacate=${this.$route.name}`,
        });
        this.videoList = res.data.data;
        console.log(this.videoList);
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
