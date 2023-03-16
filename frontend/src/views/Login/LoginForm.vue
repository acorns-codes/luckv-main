<template>
  <div class="main-container">
    <div class="form-box">
      <v-card class="mx-auto px-6 py-8" max-width="344">
        <div class="btn-box" @click="this.$store.state.isClicked = false">
          <img :src="require(`@/assets/images/close.svg`)" />
        </div>
        <h3>로그인</h3>
        <v-form v-model="form" @submit.prevent="login">
          <v-text-field
            v-model="userId"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            clearable
            required
            label="아이디"
          ></v-text-field>
          <v-text-field
            v-model="userPassword"
            type="password"
            :readonly="loading"
            :rules="[required]"
            clearable
            label="비밀번호"
          ></v-text-field>
          <v-btn
            :loading="loading"
            block
            color="success"
            size="large"
            type="submit"
            variant="elevated"
          >
            로그인
          </v-btn>
        </v-form>
        <button class="sign-up" @click="openSignUp">회원가입</button>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: false,
      userId: "",
      userPassword: "",
      loading: false,
    };
  },

  methods: {
    // 클릭 시 회원가입으로 이동
    openSignUp() {
      this.$store.state.isClicked = false;
      this.$router.push({
        path: "/signup",
      });
    },
    required(v) {
      return !!v || "빈칸을 채워주세요!";
    },
    // 로그인 확인
    async login() {
      try {
        if (!this.form) return;
        this.loading = true;
        setTimeout(() => (this.loading = false), 2000);
        const userData = {
          mid: this.userId,
          pwd: this.userPassword,
        };
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: "http://localhost:80/login",
          data: userData,
        });
        console.log(res);
        if (userData === "" || res.data === "") {
          console.log("로그인불가");
          alert("아이디 또는 비밀번호를 확인하세요.");
        } else {
          console.log("로그인성공");
          // 로그인 성공이면 받아온 data를 json 화 하여 sessionStorageData에 저장
          this.$store.commit(
            "setSessionStorage",
            JSON.stringify(res.data.data)
          );
          // 세션에 저장된 데이터를 다시 객체화 시켜 저장
          this.$store.commit(
            "readSessionStorage",
            this.$store.state.sessionStorageData
          );

          alert(`${res.data.data.name}님 환영합니다!`);
          this.$store.state.isClicked = false;
          this.$router.push({
            path: "/",
          });
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
h3 {
  font-size: 1.5rem;
  padding-bottom: 10px;
}

.main-container {
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.496);
}
.form-box {
  width: 500px;
}
.btn-box {
  display: flex;
  justify-content: flex-end;
  & > img {
    width: 10px;
    cursor: pointer;
  }
}
.sign-up {
  padding-top: 10px;
  border-bottom: 1px solid #343434;
}
</style>
