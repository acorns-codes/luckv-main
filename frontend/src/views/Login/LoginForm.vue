<template>
  <article>
    <div class="login-root">
      <div class="login-form-box">
        <v-card class="mx-auto px-6 py-8" max-width="344">
          <div class="login-btn-box" @click="close">
            <img :src="require(`@/assets/images/close.svg`)" />
          </div>
          <h3>로그인</h3>
          <v-form v-model="form" @submit.prevent="login">
            <v-text-field
              v-model="userId"
              :rules="[required]"
              class="mb-2"
              clearable
              required
              label="아이디"
            ></v-text-field>
            <v-text-field
              v-model="userPassword"
              type="password"
              :rules="[required]"
              clearable
              label="비밀번호"
            ></v-text-field>
            <v-btn
              block
              color="warning"
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
  </article>
</template>

<script>
import { apiSignIn } from "@/api/user";
export default {
  data() {
    return {
      form: false,
      userId: "",
      userPassword: "",
    };
  },

  mounted() {
    // 스크롤을 막을 HTML 태그 선택
    // if (this.$store.state.isClicked == true) {
    //   const html = document.querySelector("html");
    //   // HTML 태그에 스크롤을 막는 CSS 속성 추가
    //   html.style.overflow = "hidden";
    // }
  },
  methods: {
    // 클릭 시 회원가입으로 이동
    openSignUp() {
      this.$store.state.isClicked = false;
      this.$router.push({
        path: "/signup",
      });
    },
    close() {
      this.$store.state.isClicked = false;
    },
    required(v) {
      return !!v || "빈칸을 채워주세요!";
    },
    // 로그인 확인
    async login() {
      try {
        if (!this.form) return;
        const userData = {
          mid: this.userId,
          pwd: this.userPassword,
        };
        const res = await apiSignIn(userData);
        console.log(res, "로그인폼");
        if (res.data) {
          this.$store.commit("getUserId", res.data.mid);
          // 로그인 성공이면 받아온 data를 json 화 하여 sessionStorageData에 저장
          this.$store.commit("setSessionStorage", JSON.stringify(res.data));
          // 세션에 저장된 데이터를 다시 객체화 시켜 저장
          this.$store.commit(
            "readSessionStorage",
            this.$store.state.sessionStorageData
          );
          alert(`${res.data.name}님 환영합니다!`);
          this.close();
        } else {
          this.close();
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
