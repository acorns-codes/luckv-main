<template>
  <div class="main-container">
    <div class="form-box">
      <v-card class="mx-auto px-6 py-8">
        <h3>회원가입</h3>
        <v-form v-model="valid" fast-fail @submit.prevent="signUp">
          <v-text-field
            v-model="id"
            label="아이디"
            :rules="idRules"
            :disabled="state == 'ins' ? false : true"
            @blur="idCheck"
            required
          ></v-text-field>

          <v-text-field
            v-model="passWord"
            label="비밀번호"
            type="password"
            :rules="passWordRules"
            required
          ></v-text-field>
          <v-text-field
            v-model="name"
            label="이름"
            :rules="nameRules"
            ß
          ></v-text-field>
          <v-text-field
            v-model="ph"
            label="휴대전화"
            :rules="phRules"
            required
          ></v-text-field>
          <v-text-field
            v-model="birthDate"
            label="생년월일"
            type="date"
            :rules="birthDateRules"
            required
          ></v-text-field>
          <v-radio-group inline label="회원구분" v-model="auth" required>
            <v-radio label="판매자" value="S"></v-radio>
            <v-radio label="구매자" value="B"></v-radio>
          </v-radio-group>
          <!-- 판매자 선택시에만 나올 수 있도록 -->
          <template v-if="`${this.auth}` === 'S'">
            <v-select
              required
              v-model="bank"
              :items="bankList"
              :rules="bankRules"
              label="은행"
            ></v-select>
            <v-text-field
              required
              v-model="account"
              :rules="accountRules"
              label="계좌번호"
            ></v-text-field>
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
      valid: false,
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
      nameRules: [
        (v) =>
          this.state === "ins" ? !!v || "이름은 필수 입력사항입니다." : true,
      ],
      ph: "",
      phRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "휴대전화는 필수 입력사항입니다."
            : true,
        (v) => /^[0-9]+/g.test(v) || "숫자만 입력해주세요",
      ],
      birthDate: "",
      birthDateRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "생년월일은 필수 입력사항입니다."
            : true,
      ],
      auth: "B",
      authRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "회원구분은 필수 입력사항입니다."
            : true,
      ],
      bank: "",
      bankRules: [
        (v) =>
          this.state === "ins" ? !!v || "은행은 필수 입력사항입니다." : true,
      ],
      bankList: ["국민", "농협", "기업", "카카오", "신한"],
      account: "",
      accountRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "계좌번호는 필수 입력사항입니다."
            : true,
      ],
    };
  },
  watch: {
    valid: {
      handler(item) {
        console.log(item);
      },
      immediate: true,
    },
  },
  methods: {
    // 회원가입
    async signUp() {
      try {
        const userData = {
          mid: this.id,
          pwd: this.passWord,
          name: this.name,
          ph: this.ph,
          birthDate: this.birthDate,
          auth: this.auth,
          account:
            this.auth === "S" ? `${this.bank}:` + this.account : "해당없음",
        };

        console.log(userData);

        if (!this.valid) {
          console.log(this.valid);
          alert("가입 형식을 지켜주세요!");
          return;
        } else {
          // 회원가입
          const res = await this.$axios({
            headers: {
              "Content-type": "application/json",
            },
            method: "POST",
            url: "http://localhost:80/addMember",
            data: userData,
          });
          console.log(res);
          console.log("회원가입완료");
          this.$router.push({
            path: "/",
          });
        }
        // console.log(this.valid);
      } catch (error) {
        console.log(error);
      }
    },
    // 아이디 중복 체크
    async idCheck() {
      const res = await this.$axios({
        method: "POST",
        url: "http://localhost:80/getId",
        data: { mid: this.id },
      });
      console.log(res);
      if (!res.data.data) {
        return;
      } else {
        alert("중복된 아이디입니다!");
        this.id = "";
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
