<template>
  <MypageNav />
  <div class="container">
    <div>
      <div>
        <h1>회원정보수정</h1>
      </div>
      <!-- 회원정보불러오기 -->
      <div class="form-box">
        <v-from v-model="valid" @submit.prevent="editInfo">
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
            v-model="this.birthDate"
            type="date"
            :rules="birthDateRules"
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
          <!-- <template v-if="`${this.auth}` === 'A'">
            <v-select v-model="bank" :items="bankList" label="은행"></v-select>
            <v-text-field
              v-model="account"
              label="계좌번호"
              model-value="12345678"
            ></v-text-field>
          </template> -->
          <v-btn
            type="submit"
            block
            color="success"
            variant="elevated"
            class="mt-2"
            >회원정보수정</v-btn
          >
        </v-from>
      </div>
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";

export default {
  components: {
    MypageNav,
  },
  data() {
    return {
      sessionData: this.$store.state.sessionStorageData,
      userData: this.$store.state.userData,
      valid: false,
      id: "아이디수정불가",
      pwd: "비밀번호입력자리",
      name: "이름수정불가",
      ph: "휴대전화입력자리",
      auth: "B",
      birhDate: "1993-05-11",
      bank: "은행변경가능",
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
      try {
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: "http://localhost:80/infoMember/",
          data: { mno: this.sessionData.mno },
        });
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
    },
    async editInfo() {
      console.log("회원정보수정");
      try {
        const res = await this.$axios({
          method: "POST",
          url: `http://localhost:80/updateMember`,
          data: {
            mno: this.sessionData.mno,
            ph: this.sessionData.ph,
            pwd: this.sessionData.pwd,
          },
        });
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {
    this.getuserInfo();
    console.log("userinfo 받아오기");
    console.log(`mno: ${this.sessionData.mno}`);
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  & > div {
    width: 1440px;
    padding: 90px;
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
