<template>
  <!-- 댓글 목록 -->
  <CommentListItem
    @getQnACommentList="getQnACommentList"
    :commentList="commentList"
  />
  <!-- 댓글 등록 -->
  <div>
    <CommentCreate
      v-if="this.$store.state.sessionStorageData.auth === 'A'"
      @getQnACommentList="getQnACommentList"
    />
  </div>
</template>
<script>
import CommentListItem from "./CommentListItem.vue";
import CommentCreate from "./CommentCreate.vue";
import { apiGetQnaAnswerList } from "@/api/qna";
export default {
  name: "PrCommentList",
  components: { CommentCreate, CommentListItem },
  props: {
    CommentList: String,
  },
  data() {
    return {
      commentList: [],
    };
  },
  methods: {
    async getQnACommentList() {
      // console.log("댓글가져오기");
      const req = {
        qno: this.$route.params.no,
      };
      try {
        const res = await apiGetQnaAnswerList(req);
        this.commentList = res.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
  async mounted() {
    await this.getQnACommentList();
  },
};
</script>
