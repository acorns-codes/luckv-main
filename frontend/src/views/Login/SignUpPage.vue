<template>
  <div class="main-container">
    <div class="form-box">
      <v-card class="mx-auto px-6 py-8">
        <h3>회원가입</h3>
        <v-form fast-fail @submit.prevent="signUp">
          <v-text-field
            v-model="id"
            label="아이디"
            :rules="idRules"
          ></v-text-field>
          <v-text-field
            v-model="passWord"
            label="비밀번호"
            type="password"
            :rules="passWordRules"
          ></v-text-field>
          <v-text-field v-model="name" label="이름"></v-text-field>
          <v-text-field
            v-model="ph"
            label="휴대전화"
            :rules="phRules"
          ></v-text-field>
          <v-text-field
            v-model="birthDate"
            label="셍년월일"
            type="date"
            :rules="birthDateRules"
          ></v-text-field>

          <v-radio-group inline label="회원구분" v-model="auth">
            <v-radio label="판매자" value="A"></v-radio>
            <v-radio label="구매자" value="B"></v-radio>
          </v-radio-group>
          <!-- 판매자 선택시에만 나올 수 있도록 -->
          <v-select
            v-model="bank"
            :hint="힌트"
            :items="bankList"
            label="은행"
            persistent-hint
            return-object
            single-line
          ></v-select>

          <v-text-field
            v-model="account"
            label="계좌번호"
            :rules="acoountRules"
          ></v-text-field>

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
      id: "",
      passWord: "",
      name: "",
      ph: "",
      birthDate: "",
      auth: "",
      bank: "",
      bankList: ["국민", "농협", "기업", "카카오", "신한"],
      account: "",
      idRules: [
        (value) => {
          if (/^[a-z]+[a-z0-9]{5,19}$/g.test(value)) return true;
          return "영문자로 시작하는 영문자 또는 숫자 6-20자로 만들어야 합니다.";
        },
      ],

      passWordRules: [
        (value) => {
          if (/^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/.test(value))
            return true;
          return "8~16자 영문, 숫자 조합으로 입력해주세요";
        },
      ],
      phRules: [
        (value) => {
          if (/^[0-9]{11}$/.test(value)) return true;
          return "숫자 11자리 이하로 입력해주세요";
        },
      ],
      accountRules: [
        (value) => {
          if (/^[0-9]+$/.test(value)) return true;
          return "숫자만 입력해주세요";
        },
      ],
    };
  },
  methods: {
    async signUp() {
      try {
        const userData = {
          mid: this.id,
          pwd: this.passWord,
          name: this.name,
          ph: this.ph,
          birth_date: this.birthDate,
          auth: this.auth,
          account: this.bank + this.account,
        };
        console.log(userData);
        // const res = await registerUser(userData);
        // console.log(res);
        console.log("회원가입완료");
        this.$router.push({
          path: "/",
        });
      } catch (error) {
        console.log(error);
      }
    },
  },
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
