<template>
  <FirstSection />
  <SecondSection />
  <!-- <ThirdSection /> -->
  <div id="f-div">
    <h2>다양한 분야의 동영상을 만나보세요</h2>
    <p>
      개성이 가득한 다양한 분야의 동영상을 먼저 선점할 수 있는 기회를노려보세요!
    </p>
  </div>
  <!-- new -->
  <HeaderSection :kind="kind[0]" />
  <!-- last -->
  <HeaderSection :kind="kind[1]" />
  <!-- free -->
  <HeaderSection :kind="kind[2]" />
  <!-- sub -->
  <HeaderSection :kind="kind[3]" />
</template>

<script>
import FirstSection from "./MainPage/FirstSection.vue";
import SecondSection from "./MainPage/SecondSection.vue";
import HeaderSection from "@/components/HeaderSection.vue";
export default {
  name: "MainPage",
  components: {
    FirstSection,
    SecondSection,
    HeaderSection,
  },
  computed: {},
  data() {
    return {
      kind: [
        {
          name: "NEW",
          content: "새로 등록된 경매",
          reqModel: {
            kind: "경매",
            page: 1,
            rowCnt: 4,
            sort: 2,
          },
        },
        {
          name: "Last Auction",
          content: "마감시간이 임박한 경매",
          reqModel: {
            kind: "경매",
            page: 1,
            rowCnt: 4,
            sort: 1,
          },
        },
        {
          name: "FREE",
          content: "무료로 제공되는 동영상",
          reqModel: {
            kind: "무료",
            page: 1,
            rowCnt: 4,
          },
        },
        {
          name: "SUBSCRIPTION",
          content: "구독 회원들에게만 제공되는 동영상",
          reqModel: {
            kind: "구독",
            page: 1,
            rowCnt: 4,
          },
        },
      ],
    };
  },
  async created() {},
};
</script>

<style lang="scss" scoped>
#f-div {
  text-align: center;
  padding: 10% 0;
  & > h2 {
    font-size: 4rem;
  }
  & > p {
    font-size: 2rem;
  }
}

@media (max-width: 767px) {
  #f-div {
    text-align: center;
    padding: 10% 0;
    & > h2 {
      font-size: 2rem;
    }
    & > p {
      font-size: 1rem;
    }
  }
}
</style>
