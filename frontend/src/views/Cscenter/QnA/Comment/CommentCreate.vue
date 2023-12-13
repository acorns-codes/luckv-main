<template>
  <div>
    <v-textarea
      label="댓글"
      placeholder="댓글을 입력하세요"
      variant="outlined"
      v-model="comment"
    ></v-textarea>
    <v-btn @click="createComment">댓글달기</v-btn>
  </div>
</template>

<script>
import { apiAddAnswer } from "@/api/qna";
export default {
  data() {
    return {
      comment: "",
    };
  },
  methods: {
    async createComment() {
      const commnetData = {
        no: this.$route.params.no,
        aid: this.$store.state.sessionStorageData.mno,
        comment: this.comment,
      };
      try {
        const res = await apiAddAnswer(commnetData);
        if (res) {
          alert("댓글 등록 완료!");
          this.$emit("getQnACommentList");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
