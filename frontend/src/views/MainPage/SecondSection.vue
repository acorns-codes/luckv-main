<template>
  <section>
    <div>
      <h3>마감이 임박한 동영상입니다.</h3>
      <p>마감이 얼마 남지 않은 동영상입니다.</p>
      <p>합리적인 가격으로 나만의 것으로 동영상을 소유해보세요!</p>
      <div class="deadlineData" v-for="(item, index) in data" :key="index">
        <div>
          <h4>{{ item.title }}</h4>
          <p>{{ item.content }}</p>
          <p><span>마감일자</span> {{ item.end }}</p>
          <div>
            <p>시작가</p>
            <p>{{ item.startprice }}</p>
            <p>{{ item.seller_id }}</p>
          </div>
          <div>
            <p>최고가</p>
            <p>{{ item.topprice }}</p>
            <p>{{ item.user_id }}</p>
          </div>

          <v-btn variant="flat" color="#FF9414"> 입찰 </v-btn>
        </div>
        <div>
          <img :src="item.thumbnail" />
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      data: [
        {
          thumbnail:
            "https://img.freepik.com/free-photo/rear-view-excited-fans-having-fun-music-festival-taking-pictures-stage-with-their-smart-phones-copy-space_637285-607.jpg?w=900&t=st=1678263326~exp=1678263926~hmac=acca962073b1fd2598a218c9d18d688541746b56d4bd525b92a9f521ec5a6bbe",
          title: "3D 영화를 보고 있는 남자",
          content:
            "교정하고 있는 남자아이가 ㄴ3D안경을 착용하고 팝콘을 먹으면서 웃고 있는 남자입니다.",

          end: "2023.04.01 토요일 12:00",
          startprice: "12,345원",
          seller_id: "yeonju05**",
          topprice: "34,567원",
          user_id: "kuro**",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
  height: 630px;
  display: flex;
  justify-content: center;
  & > div {
    width: 1240px;
    padding: 90px;
    display: flex;
    flex-direction: column;
    text-align: end;
  }
}
.deadlineData {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  & > div:nth-child(1) {
    width: 725px;
    display: flex;
    flex-direction: column;
    align-items: center;
    & span {
      font-size: 20px;
      font-weight: bold;
      color: #ff5e5e;
    }

    & > div {
      display: flex;
      width: 50%;
      padding: 5px;
      justify-content: space-between;
      & > p:nth-child(1) {
        background-color: #343434;
        color: white;
        border-radius: 5px;
        padding: 4px 5px 0 5px;
        font-size: 0.8rem;
      }
      & > p:not(:first-of-type) {
        width: 100px;
      }
    }
  }
  & > div:nth-child(2) {
    width: 485px;
    & > img {
      width: 360px;
      height: 350px;
      object-fit: cover;
    }
  }
}
button {
  width: 200px;
  margin: 15px;
}
</style>
