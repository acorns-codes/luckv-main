<template>
  <!-- 댓글 목록 -->
  <CommentListItem />
  <!-- 댓글 등록 -->
  <div>
    <CommentCreate v-if="this.$store.state.sessionStorageData.auth === 'A'" />
  </div>
</template>
<script>
import CommentListItem from "./CommentListItem.vue";
import CommentCreate from "./CommentCreate.vue";

export default {
  name: "PrCommentList",
  components: { CommentCreate, CommentListItem },
  props: {
    CommentList: String,
  },
  data() {
    return {};
  },
};
</script>
