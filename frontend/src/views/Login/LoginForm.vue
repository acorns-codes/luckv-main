<template>
  <div class="main-container">
    <template v-if="!userData">
      <div class="login-box">
        <v-card class="mx-auto px-6 py-8" max-width="344">
          <div class="btn-box" @click="this.$store.state.isClicked = false">
            <img :src="require(`@/assets/images/close.svg`)" />
          </div>
          <h3>로그인</h3>
          <v-form v-model="form" @submit.prevent="login" method="post">
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
    </template>
    <template v-else>
      <SignUpForm />
    </template>
  </div>
</template>

<script>
// import axios from "axios";

// import { loginUser } from "@/api/index";
import SignUpForm from "./SignUpForm.vue";


export default {
  components: {
    SignUpForm,
  },
  data() {
    return {
      form: false,
      userId: null,
      userPassword: null,
      loading: false,
      userData: false,
    };
  },
  methods: {
    openSignUp() {
      this.userData = !this.userData;
      // this.$store.state.isClicked = false;
    },
    required(v) {
      return !!v || "빈칸을 채워주세요!";
    },
    // async login() {
    //   try {
    //     console.log("로그인");
    //     // if (!this.form) return;
    //     // this.loading = true;
    //     // setTimeout(() => (this.loading = false), 2000);


    //     const userData = {
    //       mid: this.userId,
    //       pwd: this.userPassword,
    //     };
    //     console.log(userData);
    //     const { data } = await loginUser(userData);
    //     // this.$store.commit("setUserId", data.login[0].userId);
    //     console.log(data);
    //     this.$store.state.isClicked = false;
    //   } catch (error) {
    //     console.log(error);
    //   }
    // },
    login() {
      const userData = {
        mid: this.userId,
        pwd: this.userPassword,
      };
      this.$axios.post("http://localhost:8080/login", userData);

        const userData = {
          mid : this.userId,
          pwd: this.userPassword,
        };
        console.log(userData);
        const { data } = await loginUser(userData);
        this.$store.commit("setUserId", data.login[0].userId);
        this.$store.state.isClicked = false;
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
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.496);
}
.login-box {
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
