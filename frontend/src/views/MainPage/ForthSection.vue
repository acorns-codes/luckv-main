<template>
  <section>
    <div>
      <div>
        <h2>신규</h2>
      </div>
      <div class="card-box">
        <v-card class="mx-auto">
          <div>
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
              height="200px"
              cover
            ></v-img>
          </div>
          <v-card-title> 제목</v-card-title>
          <v-card-text>내용</v-card-text>
          <v-card-subtitle>
            <span>시작일자</span> 시작일자여기ㅏㅇ
          </v-card-subtitle>
          <v-card-subtitle>
            <span>마감일자</span> 마감일자 여기에
          </v-card-subtitle>
          <div class="pay-box">
            <p>시작가</p>
            <p>얼마얼마 원</p>
            <p>yeonju05**</p>
          </div>
          <div class="pay-box">
            <p>최고가</p>
            <p>얼마얼마 원</p>
            <p>diswn1**</p>
          </div>
        </v-card>
        <v-card class="mx-auto">
          <div>
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
              height="200px"
              cover
            ></v-img>
          </div>
          <v-card-title> 제목</v-card-title>
          <v-card-text>내용</v-card-text>
          <v-card-subtitle>
            <span>시작일자</span> 시작일자여기ㅏㅇ
          </v-card-subtitle>
          <v-card-subtitle>
            <span>마감일자</span> 마감일자 여기에
          </v-card-subtitle>
          <div class="pay-box">
            <p>시작가</p>
            <p>얼마얼마 원</p>
            <p>yeonju05**</p>
          </div>
          <div class="pay-box">
            <p>최고가</p>
            <p>얼마얼마 원</p>
            <p>diswn1**</p>
          </div>
        </v-card>
        <v-card class="mx-auto">
          <div>
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
              height="200px"
              cover
            ></v-img>
          </div>
          <v-card-title> 제목</v-card-title>
          <v-card-text>내용</v-card-text>
          <v-card-subtitle>
            <span>시작일자</span> 시작일자여기ㅏㅇ
          </v-card-subtitle>
          <v-card-subtitle>
            <span>마감일자</span> 마감일자 여기에
          </v-card-subtitle>
          <div class="pay-box">
            <p>시작가</p>
            <p>얼마얼마 원</p>
            <p>yeonju05**</p>
          </div>
          <div class="pay-box">
            <p>최고가</p>
            <p>얼마얼마 원</p>
            <p>diswn1**</p>
          </div>
        </v-card>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        {
          title: "Pre-fab homes",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 12,
        },
        {
          title: "Favorite road trips",
          src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
          flex: 6,
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 6,
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 6,
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 6,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  display: flex;
  justify-content: center;
  & > div {
    width: 1240px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}

.card-box {
  width: 960px;
  display: flex;
  flex-wrap: wrap;
  padding: 30px;
  margin: 30px;
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
span {
  color: #ff5e5e;
  font-weight: bolder;
}
</style>
