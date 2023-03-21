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
          url: `http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/qnaAnswer`,
          data: commnetData,
        });
        console.log(res);
        window.location.reload();
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
