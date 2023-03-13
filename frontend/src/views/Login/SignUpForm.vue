<template>
  <div class="main-container">
    <v-card class="mx-auto px-6 py-8" max-width="450">
      <v-form fast-fail @submit.prevent="signUp">
        <v-text-field
          v-model="id"
          label="아이디"
          :rules="idRules"
        ></v-text-field>
        <v-text-field
          v-model="passWord"
          label="비밀번호"
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
        <v-radio-group v-model="radios" inline>
          <template v-slot:label>
            <div>회원구분</div>
          </template>
          <v-radio value="seller">
            <template v-slot:label>
              <div>판매자</div>
            </template>
          </v-radio>
          <v-radio value="user">
            <template v-slot:label>
              <div>구매자</div>
            </template>
          </v-radio>
        </v-radio-group>
        <!-- 판매자 선택시에만 나올 수 있도록 -->

        <br />
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

        <v-btn type="submit" block class="mt-2">Submit</v-btn>
      </v-form>
    </v-card>
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
    // async signUp() {
    //   console.log("폼 제출");
    //   const userData = {
    //     id: this.id,
    //     passWord: this.passWord,
    //     name: this.name,
    //     ph: this.ph,
    //     birthDate: this.birthDate,
    //     auth: this.auth,
    //     bank: this.bank + this.account,
    //   };
    //   const { data } = await registerUser(userData);
    //   console.log(data.name);
    // },
  },
};
</script>

<style></style>
