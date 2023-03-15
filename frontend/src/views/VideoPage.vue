<template>
  <div class="video">
    <h1>비디오페이지</h1>
    <div v-for="item in videoData" :key="item">
      <v-card class="mx-auto" max-width="344">
        <v-img
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
          height="200px"
          cover
        ></v-img>
        <v-card-title> {{ item.title }} </v-card-title>
        <v-card-text> {{ item.content }} </v-card-text>
        <v-card-subtitle>
          <span>시작일자</span> {{ item.startDay }}
          {{ item.startTime }}
        </v-card-subtitle>
        <v-card-subtitle>
          <span>마감일자</span> {{ item.lastDay }}
          {{ item.lastTime }}
        </v-card-subtitle>
        <div class="pay-box">
          <p>시작가</p>
          <p>{{ item.payStart }}</p>
          <p>yeonju05**</p>
        </div>
        <div class="pay-box">
          <p>최고가</p>
          <p>{{ item.payMax }}</p>
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
                    <v-text-field suffix="원"></v-text-field>
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
</template>

<script>
export default {
  data() {
    return {
      show: false,
      videoData: [
        {
          title: "노을이 지는 영상",
          content: "붉은 노을이 지고 있는 영상입니다.",
          startDay: "2023-03-03",
          startTime: "12:00",
          lastDay: "2023-03-31",
          lastTime: "18:00",
          payStart: "20000원",
          payMax: "28000원",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
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
</style>
