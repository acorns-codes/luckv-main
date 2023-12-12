<template>
  <button v-if="isUserLogin" class="sub-btn-box" @click="sub">구독</button>
  <div>
    <HeaderLayout />
    <slot />
  </div>
</template>

<script>
import HeaderLayout from "./HeaderLayout.vue";
import { apiPostVideo } from "@/api/user";
export default {
  name: "AppLayoutHome",
  components: { HeaderLayout },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
  },
  methods: {
    // 구독신청
    async sub() {
      console.log(this.$store.state.subAuth);
      if (this.$store.state.subAuth === "Y") {
        alert("이미 구독중입니다!");
      } else if (this.$store.state.sessionStorageData.auth !== "B") {
        alert("구매자 회원만 구독이 가능합니다!");
      } else {
        if (!confirm("구독을 신청하시겠습니까?")) {
          alert("구독 신청이 완료되지 못했습니다!");
        } else {
          const req = {
            mno: this.$store.state.sessionStorageData.mno,
          };
          try {
            const res = await apiPostVideo(req);
            console.log(res);
            if (res.data) {
              alert("구독 신청이 완료되었습니다!");
              this.$store.commit("storeSubAuth", "Y");
            }
          } catch (error) {
            console.log(error);
          }
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
  background-color: #ff9414;
  font-weight: bold;
  position: fixed;
  bottom: 30px;
  right: 30px;
  animation: motion 0.3s linear 0s infinite alternate;
  margin-top: 0;
  border-radius: 100%;
  box-shadow: 5px 5px 10px #b2b2b2;
  z-index: 2;
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
