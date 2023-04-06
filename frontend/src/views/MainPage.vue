<template>
  <FirstSection />
  <SecondSection />
  <!-- <ThirdSection /> -->
  <div id="f-div">
    <h2 style="font-size: 70px">다양한 분야의 동영상을 만나보세요</h2>
    <p style="font-size: 30px">
      개성이 가득한 다양한 분야의 동영상을 먼저 선점할 수 있는 기회를노려보세요!
    </p>
  </div>
  <HeaderSection :kind="kind[0]" />
  <HeaderSection :kind="kind[1]" />
  <HeaderSection :kind="kind[2]" />
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
  data() {
    return {
      kind: [
        {
          name: "NEW",
          content: "새로 등록된 경매",
          api: "auctionNew",
        },
        {
          name: "Last Auction",
          content: "마감시간이 임박한 경매",
          api: "auctionDeadline",
        },
        {
          name: "FREE",
          content: "무료로 제공되는 동영상",
          api: "auctionFree",
        },
        {
          name: "SUBSCRIPTION",
          content: "구독 회원들에게만 제공되는 동영상",
          api: "auctionSubs",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
#f-div {
  text-align: left;
  width: 100%;
  padding: 10% 0 0% 10%;
}
</style>
