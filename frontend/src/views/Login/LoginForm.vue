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
            v-model="mid"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            clearable
            required
            label="아이디"
          ></v-text-field>
          <v-text-field
            v-model="pwd"
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
// import { loginUser } from "@/api/index";

export default {
  data() {
    return {
      form: false,
      mid: "",
      pwd: "",
      loading: false,
      name: "",
    };
  },
  methods: {
    openSignUp() {
      this.$store.state.isClicked = false;
      this.$router.push({
        path: "/signup",
      });
    },
    required(v) {
      return !!v || "빈칸을 채워주세요!";
    },
    async login() {
      try {
        console.log("로그인");
        if (!this.form) return;
        this.loading = true;
        setTimeout(() => (this.loading = false), 2000);
        // const userData = {
        //   mid: this.mid,
        //   pwd: this.pwd,
        // };
        // console.log(userData);
        // console.log(this.mid);
        const res = await this.$axios({
          headers: {
            "Content-type": "application/x-www-form-urlencoded",
          },
          method: "POST",
          url: "http://localhost:8080/login",
          data: {
            mid: this.mid,
            pwd: this.pwd,
          },
        });
        console.log(res);
        console.log("로그인성공");
        sessionStorage.setItem("login", JSON.stringify(res.data));
        alert(`${this.mid}님 환영합니다!`);
        this.$router.push({
          path: "/",
        });
        // if (userData === "") {
        //   alert("아이디 또는 비밀번호를 확인하세요.");
        //   this.mid.val("");
        //   this.pwd.val("");
        // } else {
        //   // 로그인 정보를 세션에 저장
        //   sessionStorage.setItem("login", JSON.stringify(userData));
        //   alert(`${this.mid}님 환영합니다!`);
        //   this.$router.push({
        //     path: "/",
        //   });

        //     // this.$store.commit("setUserId", data.login[0].userId);
        //     console.log(data);
        // this.$store.state.isClicked = false;
        // }
      } catch (error) {
        console.log(error);
      }
      // },
      // login() {
      //   const userData = {
      //     mid: this.userId,
      //     pwd: this.userPassword,
      //   };
      //   this.$axios
      //     .post("http://localhost:8080/login", userData)
      //     .then((res) => {
      //       console.log(res);
      //     })
      //     .then((userData) => {
      //       console.log(userData);
      //       sessionStorage.setItem("login", JSON.stringify(userData));
      //       alert(`${userData.id}님 환영합니다!`);
      //     })
      //     .catch((error) => {
      //       console.log(error);
      //     });
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
