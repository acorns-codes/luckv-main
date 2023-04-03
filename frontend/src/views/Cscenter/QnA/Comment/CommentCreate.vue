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
export default {
  data() {
    return {
      comment: "",
    };
  },
  mounted() {
    console.log(this.$route.params.no);
  },
  methods: {
    async createComment() {
      console.log("댓글 달기");
      console.log(typeof Number(this.$route.params.no));
      const commnetData = {
        no: this.$route.params.no,
        aid: this.$store.state.sessionStorageData.mno,
        comment: this.comment,
      };
      try {
        const res = await this.$axios({
          headers: {
            "Content-type": "application/json",
          },
          method: "POST",
          url: `${process.env.VUE_APP_API_URL}/qnaAnswer`,
          data: commnetData,
        });
        console.log(res);
        this.$router.go();
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
