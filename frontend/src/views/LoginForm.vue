<template>
  <div class="main-container">
    <div class="login-box">
      <v-card class="mx-auto px-6 py-8" max-width="344">
        <h3>로그인</h3>
        <v-form v-model="form" @submit.prevent="onSubmit" method="post">
          <v-text-field
            v-model="id"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            clearable
            label="아이디"
          ></v-text-field>
          <v-text-field
            v-model="password"
            :readonly="loading"
            :rules="[required]"
            clearable
            label="비밀번호"
          ></v-text-field>
          <v-btn
            :disabled="!form"
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
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: false,
      id: null,
      password: null,
      loading: false,
    };
  },
  methods: {
    onSubmit() {
      if (!this.form) return;
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
      this.$emit("onSubmit", this.form);
    },
    required(v) {
      return !!v || "빈칸을 채워주세요!";
    },
  },
  props: {
    isClicked: Boolean,
  },

  //   let saveData = {};
  //   saveData.userId = this.userId;
  //   saveData.userPassword = this.userPassword;

  // try {
  //   this.$axios
  //     .post(PORT, JSON.stringify(saveData), {
  //       headers: {
  //         "Content-Type": `application/json`,
  //       },
  //     })
  //     .then((res) => {
  //       if (res.status === 200) {
  //         // 로그인 성공시 처리해줘야할 부분
  //         this.$store.commit("login", res.data);
  //         this.$router.push("/login", res.data);
  //       }
  //     });
  // } catch (error) {
  //   console.error(error);
  // }
};
</script>

<style>
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
</style>
