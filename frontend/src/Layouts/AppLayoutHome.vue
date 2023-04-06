<template>
  <button class="sub-btn-box" @click="sub">구독</button>
  <div>
    <HeaderLayout />
    <slot />
  </div>
</template>

<script>
import HeaderLayout from "./HeaderLayout.vue";
export default {
  name: "AppLayoutHome",
  components: { HeaderLayout },
  methods: {
    // 구독신청
    async sub() {
      if (!confirm("구독을 신청하시겠습니까?")) {
        alert("구독 신청이 완료되지 못했습니다!");
      } else {
        try {
          const res = await this.$axios({
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/videoSubYn`,
            data: { mno: this.$store.state.sessionStorageData.mno },
          });
          console.log(res);
          if (res.data.data) {
            alert("구독 신청이 완료되었습니다!");
            this.$store.commit("storeSubAuth", "Y");
          }
        } catch (error) {
          console.log(error);
        }
      }
    },
  },
};
</script>

<style scoped>
.sub-btn-box {
  width: 70px;
  height: 70px;
  background-color: #ff9414;;
  font-weight: bold;
  position: fixed;
  bottom: 30px;
  right: 30px;
  animation: motion 0.3s linear 0s infinite alternate;
  margin-top: 0;
  border-radius: 100%;
  box-shadow: 5px 5px 10px #b2b2b2;
}
.sub-btn-box:hover {
  background-color: #5f9ea0;
}

@keyframes motion {
  0% {
    margin-top: 0px;
  }
  100% {
    margin-top: 10px;
  }
}
</style>
