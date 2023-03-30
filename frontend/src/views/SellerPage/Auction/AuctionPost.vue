<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="mypage">
        <h2>동영상 등록</h2>
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
              :items="categorys"
              item-title="title"
              item-value="value"
              v-model="category"
            ></v-select>
            <v-file-input
              label="동영상 올리기"
              prepend-icon="mdi-video"
              v-model="video"
              :rules="videoRules"
              required
            ></v-file-input>
            <v-radio-group inline label="동영상 구분" v-model="kind" required>
              <v-radio label="경매" value="A"></v-radio>
              <v-radio label="무료" value="B"></v-radio>
              <v-radio label="구독" value="C"></v-radio>
            </v-radio-group>
            <template v-if="this.kind === 'A'">
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
            </template>
            <template v-else-if="this.kind === 'B'"></template>
            <template v-else>
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
            </template>
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
      status: "",
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
      kind: "A",
      category: "",
      categoryList: ["동물", "인물", "건물", "식물", "기타"],
      categorys: [
        {
          title: "동물",
          value: "animal",
        },
        {
          title: "인물",
          value: "character",
        },
        {
          title: "건물",
          value: "building",
        },
        {
          title: "식물",
          value: "plant",
        },
        {
          title: "기타",
          value: "etc",
        },
      ],

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

  mounted() {},
  methods: {
    getStatus() {
      const today = new Date().getTime();
      console.log(today);
      let day = `${this.startDay} ${this.startTime}:00`;
      // console.log(day);
      let daytime = new Date(day).getTime();
      // console.log(daytime);
      if (today < daytime) {
        return "경매전";
      } else {
        return "경매중";
      }
    },

    async postAuction() {
      console.log("경매등록");
      console.log(this.startDay);
      let today = new Date().getTime();
      let startday = `${this.startDay} ${this.startTime}:00`;
      let daytime = new Date(startday).getTime();
      if (today < daytime) {
        this.status = "경매전";
      } else {
        this.status = "판매중";
      }
      let postData = "";
      try {
        if (this.kind == "A") {
          postData = {
            seller: this.$store.state.sessionStorageData.mno,
            title: this.title,
            content: this.content,
            vcate: this.category,
            payStart: this.payStart,
            startDay: `${this.startDay} ${this.startTime}:00`,
            lastDay: `${this.lastDay} ${this.lastTime}:00`,
            status: this.getStatus(),
            kind:
              this.kind == "A" ? "경매" : this.kind == "B" ? "무료" : "구독",
          };
        } else if (this.kind == "B") {
          postData = {
            seller: this.$store.state.sessionStorageData.mno,
            title: this.title,
            content: this.content,
            vcate: this.category,
            payStart: "",
            startDay: "",
            lastDay: "",
            status: "",
            kind:
              this.kind == "A" ? "경매" : this.kind == "B" ? "무료" : "구독",
          };
        } else {
          postData = {
            seller: this.$store.state.sessionStorageData.mno,
            title: this.title,
            content: this.content,
            vcate: this.category,
            payStart: "",
            startDay: "",
            lastDay: `${this.lastDay} ${this.lastTime}:00`,
            status: "",
            kind:
              this.kind == "A" ? "경매" : this.kind == "B" ? "무료" : "구독",
          };
        }
        console.log(postData);
        if (!this.valid) {
          console.log(this.valid);
          alert("등록 형식을 지켜주세요!");
          return;
        } else {
          // 경매 등록
          console.log("동영상 업로드");
          const res = await this.$axios({
            headers: {
              "Content-type": "application/json",
            },
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/insertAuction`,
            data: postData,
          });
          console.log(res);
          // alert("리스트 업로드");
          console.log(this.video);
        }
      } catch (error) {
        console.log(error);
      }
      try {
        console.log("동영상 업로드");
        console.log(this.video);
        const formdata = new FormData();
        // console.log(formdata, "확인1");
        formdata.append("file", this.video[0]);
        // console.log("확인2", formdata);
        const resVideo = await this.$axios({
          headers: {
            "Content-Type": "multipart/form-data",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/videoUpload`,
          data: formdata,
        });
        console.log(resVideo);
        if (resVideo.data.data) {
          alert("새로운 경매가 등록되었습니다!");
          window.location.reload();
        }
      } catch (error) {
        console.log(error);
      }
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
