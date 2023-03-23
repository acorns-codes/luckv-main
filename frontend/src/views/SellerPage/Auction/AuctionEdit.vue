<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="mypage">
        <h2>경매 등록</h2>
        <div class="form-box">
          <v-form v-model="valid" @submit.prevent="postAuction">
            <v-text-field
              label="제목"
              v-model="title"
              :rules="titleRules"
              required
            ></v-text-field>
            <v-textarea
              label="내용"
              v-model="content"
              :rules="contentRules"
              required
            ></v-textarea>
            <v-select
              required
              label="카테고리"
              :items="categoryList"
              v-model="category"
            ></v-select>
            <v-file-input
              label="동영상 올리기"
              prepend-icon="mdi-video"
              v-model="video"
              :rules="videoRules"
              required
            ></v-file-input>
            <v-text-field
              label="경매 시작가"
              v-model="payStart"
              suffix="원"
              :rules="payStartRules"
              required
            ></v-text-field>
            <v-text-field
              label="시작 날짜"
              v-model="startDay"
              type="date"
              :rules="StartDaytRules"
              required
            ></v-text-field>
            <v-text-field
              label="시작 시간"
              v-model="startTime"
              type="time"
              :rules="startTimetRules"
              required
            ></v-text-field>
            <v-text-field
              label="마감 날짜"
              v-model="lastDay"
              type="date"
              :rules="lastDaytRules"
              required
            ></v-text-field>
            <v-text-field
              label="마감 시간"
              v-model="lastTime"
              type="time"
              :rules="lastTimetRules"
              required
            ></v-text-field>
            <v-btn
              type="submit"
              block
              color="success"
              variant="elevated"
              class="mt-2"
              >경매 등록</v-btn
            >
          </v-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";
export default {
  components: { MypageNav },
  data() {
    return {
      valid: false,
      title: "",
      titleRules: [
        (v) => !!v || "제목은 필수 입력사항입니다.",
        // (v) =>
        //   /^[A-Za-z0-9]{2,20}$/g.test(v) ||
        //   "제목은 2자 이상 20자 이하로 작성해주세요.",
      ],
      content: "",
      contentRules: [
        (v) => !!v || "내용은 필수 입력사항입니다.",
        // (v) =>
        //   /^[a-z0-9@.]{2,150}$/g.test(v) ||
        //   "내용은 2자 이상 150자 이하로 작성해주세요.",
      ],
      video: "",
      videoRules: [(v) => !!v || "비디오 등록은 필수 사항입니다."],
      category: "동물",
      categoryList: ["동물", "인물", "건물", "식물", "기타"],
      payStart: "",
      payStartRules: [(v) => !!v || "경매 시작가는 필수 입력 사항입니다."],
      startDay: "",
      StartDaytRules: [(v) => !!v || "시작 날짜는 필수 입력 사항입니다."],
      startTime: "",
      startTimetRules: [(v) => !!v || "시작 시간는 필수 입력 사항입니다."],
      lastDay: "",
      lastDaytRules: [(v) => !!v || "마감 날짜는 필수 입력 사항입니다."],
      lastTime: "",
      lastTimetRules: [(v) => !!v || "마감 시간는 필수 입력 사항입니다."],
    };
  },
  computed() {},
  methods: {
    async postAuction() {
      console.log("경매등록");
      try {
        const postData = {
          seller: this.$store.state.sessionStorageData.mno,
          title: this.title,
          content: this.content,
          vcate: this.category,
          payStart: this.payStart,
          startDay: `${this.startDay} ${this.startTime}:00`,
          lastDay: `${this.lastDay} ${this.lastTime}:00`,
        };
        console.log(postData);
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
            url: "http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/insertAuction",
            data: postData,
          });
          console.log(res);
        }
      } catch (error) {
        console.log(error);
      }
      //   try {
      //     const resVideo = await this.$axios({});
      //     console.log(resVideo);
      //   } catch (error) {
      //     console.log(error);
      //   }
    },
  },
};
</script>

<style lang="scss" scoped>
#root {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
#page-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.mypage {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  h2 {
    margin-bottom: 20px;
  }
}
.form-box {
  width: 500px;
}
</style>
