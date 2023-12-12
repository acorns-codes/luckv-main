<template>
  <div class="container">
    <div>
      <div>
        <h2>회원정보수정</h2>
      </div>
      <!-- 회원정보불러오기 -->
      <div class="form-box">
        <v-form v-model="valid" @submit.prevent="editInfo">
          <v-text-field
            label="아이디"
            v-model="this.userData.mid"
            readonly
          ></v-text-field>
          <v-text-field
            label="비밀번호"
            v-model="this.userData.pwd"
            type="password"
            :rules="passWordRules"
          ></v-text-field>
          <v-text-field
            v-model="this.userData.name"
            label="이름"
            readonly
          ></v-text-field>
          <v-text-field
            label="휴대전화"
            v-model="this.userData.ph"
            :rules="phRules"
          ></v-text-field>
          <v-text-field
            label="생년월일"
            v-model="this.userData.birthDate"
            type="date"
            readonly
          ></v-text-field>
          <v-radio-group
            inline
            label="회원구분"
            v-model="this.userData.auth"
            readonly
          >
            <v-radio label="판매자" value="S"></v-radio>
            <v-radio label="구매자" value="B"></v-radio>
          </v-radio-group>
          <!-- 판매자 선택시에만 나올 수 있도록 -->
          <template v-if="`${this.userData.auth}` === 'S'">
            <v-select
              v-model="this.accountInfo[0]"
              :items="bankList"
              label="은행"
            ></v-select>
            <v-text-field
              v-model="this.accountInfo[1]"
              label="계좌번호"
            ></v-text-field>
          </template>
          <v-btn
            type="submit"
            block
            color="success"
            variant="elevated"
            class="mt-2"
            >회원정보수정</v-btn
          >
        </v-form>
      </div>
    </div>
  </div>
</template>

<script>
import { apiGetInfoMember, apiEditInfoMember } from "@/api/user";
export default {
  mounted() {
    this.getuserInfo();
  },

  data() {
    return {
      sessionData: this.$store.state.sessionStorageData,
      accountInfo: "",
      userData: "",
      valid: false,
      bankList: ["국민", "농협", "기업", "카카오", "신한"],
      state: "ins",
      passWordRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "패스워드는 필수 입력사항입니다."
            : true,
        (v) =>
          /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/.test(v) ||
          "패스워드는 8~16자 영문 및 숫자 조합으로 입력해주세요",
      ],
      phRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "휴대전화는 필수 입력사항입니다."
            : true,
        (v) => /^[0-9]+/g.test(v) || "숫자만 입력해주세요",
      ],
      birthDateRules: [
        (v) =>
          this.state === "ins"
            ? !!v || "생년월일은 필수 입력사항입니다."
            : true,
      ],
    };
  },
  methods: {
    //회원정보불러오기
    async getuserInfo() {
      const req = {
        mno: this.sessionData.mno,
      };
      try {
        const res = await apiGetInfoMember(req);
        console.log(res);
        this.userData = res.data;
        this.accountInfo = this.userData.acccount.split(":");
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
    },
    //회원정보수정
    async editInfo() {
      console.log("회원정보수정");
      const req = {
        mno: this.userData.mno,
        ph: this.userData.ph,
        pwd: this.userData.pwd,
      };
      try {
        const res = await apiEditInfoMember(req);
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding-top: 110px;

  & > div {
    display: flex;
    flex-direction: column;
    align-items: center;
    & > div:nth-child(1) {
      display: flex;
      justify-content: flex-start;
      padding-bottom: 30px;
    }
  }
}
.form-box {
  width: 500px;
}
</style>
