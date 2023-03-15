<template>
  <div class="main-container">
    <div class="form-box">
      <v-card class="mx-auto px-6 py-8">
        <h3>회원가입</h3>
        <v-form ref="form" fast-fail @submit.prevent="signUp">
          <v-text-field
            v-model="id"
            label="아이디"
            :rules="idRules"
            :disabled="state == 'ins' ? false : true"
            required
          ></v-text-field>
          <v-text-field
            v-model="passWord"
            label="비밀번호"
            type="password"
            :rules="passWordRules"
            required
          ></v-text-field>
          <v-text-field v-model="name" label="이름"></v-text-field>
          <v-text-field
            v-model="ph"
            label="휴대전화"
            :rules="phRules"
            required
          ></v-text-field>
          <v-text-field
            v-model="birthDate"
            label="셍년월일"
            type="date"
            required
          ></v-text-field>
          <v-radio-group inline label="회원구분" v-model="auth" required>
            <v-radio label="판매자" value="S"></v-radio>
            <v-radio label="구매자" value="B"></v-radio>
          </v-radio-group>
          <!-- 판매자 선택시에만 나올 수 있도록 -->
          <template v-if="`${this.auth}` === 'S'">
            <v-select v-model="bank" :items="bankList" label="은행"></v-select>
            <v-text-field v-model="account" label="계좌번호"></v-text-field>
          </template>
          <v-btn
            type="submit"
            block
            color="success"
            variant="elevated"
            class="mt-2"
            >회원가입</v-btn
          >
        </v-form>
      </v-card>
    </div>
  </div>
</template>

<script>
// import { registerUser } from "@/api/idnex";

export default {
  data() {
    return {
      state: "ins",
      id: "",
      idRules: [
        (v) => !!v || "아이디는 필수 입력사항입니다.",
        (v) =>
          /^[a-z]+[a-z0-9]{5,19}$/g.test(v) ||
          "아이디는 영문자로 시작하는 영문자 6-20자로 입력해주세요.",
      ],
      passWord: "",
      passWordRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "패스워드는 필수 입력사항입니다."
            : true,
        (v) =>
          /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/.test(v) ||
          "패스워드는 8~16자 영문 및 숫자 조합으로 입력해주세요",
      ],
      name: "",
      ph: "",
      phRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "휴대전화는 필수 입력사항입니다."
            : true,
        (v) => /^[0-9]{11}$/.test(v) || "숫자 11자리 이하로 입력해주세요",
      ],
      birthDate: "",
      auth: "",
      bank: "",
      bankList: ["국민", "농협", "기업", "카카오", "신한"],
      account: "",
    };
  },
  methods: {
    async signUp() {
      const validate = this.$refs.form.validate();
      if (validate) {
        if (confirm("가입하시겠습니까?")) {
          const userData = {
            mid: this.id,
            pwd: this.passWord,
            name: this.name,
            ph: this.ph,
            birth_date: this.birthDate,
            auth: this.auth,
            account: this.bank + this.account,
          };
          try {
            console.log(userData);
            const res = await this.$axios.post(
              "http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/addMember",
              userData
            );
            console.log(res);
            console.log("회원가입완료");
            this.$router.push({
              path: "/",
            });
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
h3 {
  font-size: 1.5rem;
  padding-bottom: 10px;
}
.main-container {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.496);
  padding: 40px;
}
.form-box {
  width: 500px;
}
</style>
