<template>
  <SellerMypageNav />
  <div class="container">
    <div>
      <div>
        <h2>회원정보수정</h2>
      </div>
      <!-- 셀러정보불러오기 -->
      <div class="form-box" @submit.prevent="editInfo">
        <v-from>
          <v-text-field
            label="아이디"
            v-model="this.sessionData.mid"
            readonly
          ></v-text-field>
          <v-text-field
            label="비밀번호"
            v-model="this.sessionData.pwd"
            type="password"
          ></v-text-field>
          <v-text-field
            v-model="this.sessionData.name"
            label="이름"
            readonly
          ></v-text-field>
          <v-text-field
            label="휴대전화"
            v-model="this.sessionData.ph"
          ></v-text-field>
          <v-text-field
            label="생년월일"
            v-model="this.sessionData.birthDate"
            type="date"
          ></v-text-field>
          <v-radio-group
            inline
            label="회원구분"
            v-model="this.sessionData.auth"
            readonly
          >
            <v-radio label="판매자" value="S"></v-radio>
            <v-radio label="구매자" value="B"></v-radio>
          </v-radio-group>
          <!-- 판매자 선택시에만 나올 수 있도록 -->
          <template v-if="`${this.$store.state.userData.auth}` === 'S'">
            <v-select v-model="bank" :items="bankList" label="은행"></v-select>
            <v-text-field label="계좌번호" v-model="this.bank"></v-text-field>
          </template>
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
import SellerMypageNav from "@/components/SellerMypageNav.vue";
export default {
  components: {
    SellerMypageNav,
  },
  data() {
    return {
      sessionData: this.$store.state.sessionStorageData,
      userData: this.$store.state.userData,
      pwd: "비밀번호입력자리",
      name: "이름수정불가",
      ph: "휴대전화입력자리",
      auth: "A",
      birhDate: "1993-05-11",
      bank: "은행변경가능",
      bankList: ["국민", "농협", "기업", "카카오", "신한"],
      dataBank: `(${this.$store.state.userData.bank}).indexOf(:)`,
      // (this.$store.state.userData.bank).indexOf(":")`
      // dataBank:"this.$store.state.userData.bank(this.$store.state.userData.bank).indexOf(":")"
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
          url: "http://localhost:80/infoMember",
          data: { mno: this.sessionData.mno },
        });
        console.log(res);
        this.$store.commit("getUserData", res.data);
        console.log(this.$store.state.userData);
      } catch (error) {
        console.log(error);
      }
    },

    //회원정보수정
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
  },
  mounted() {
    console.log("셀러페이지");
    // console.log(this.$store.state.userData.acccount);
    // const bank = this.$store.state.userData.acccount.indexOf(":");
    // console.log(bank);
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
