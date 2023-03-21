<template>
  <div id="root">
    <div class="comment-box" v-for="item in commentList" :key="item">
      <div>
        <p>{{ item.comment }}</p>
        <p>{{ item.acreate }}</p>
      </div>
      <!-- 작성자만 수정 삭제 버튼 보일수 있게 수정  -->
      <div>
        <v-btn variant="plain" size="x-small" @click="updateComment"
          >수정</v-btn
        >
        <v-btn variant="plain" size="x-small" @click="deleteComment"
          >삭제</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PrCommentListItem",
  props: {
    commentItem: Object,
    reload: Function,
  },
  data() {
    return {
      name: "",
      commentList: "",
    };
  },
  mounted() {
    this.getQnACommentList();
  },
  methods: {
    async getQnACommentList() {
      console.log("댓글가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/qnaAnswerList?qno=${this.$route.params.no}`,
        });
        this.commentList = res.data.data;
        // console.log(this.commentList);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#root {
  margin-top: 30px;
}
.comment-box {
  display: flex;
  padding: 15px;
  border-top: 1px solid #eee;
  flex-direction: column;

  & > div:nth-child(1) {
    display: flex;
    align-items: end;
    justify-content: space-between;
    & > p:nth-child(1) {
      font-size: 0.9rem;
    }
    & > p:nth-child(2) {
      font-size: 0.5rem;
    }
  }
  & > div:nth-child(2) {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
