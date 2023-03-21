<template>
  <CommentListItem />
  <!-- 댓글 쓰기 창도 아래 컴포넌트에 따로 생성.-->
  <div>
    <!-- <PrCommentCreate :contentId="contentId" :reload="reload"/> -->
    <CommentCreate />
  </div>
  <!-- </div> -->
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
    return {
      //   commentObj : data.Comment.filter(item => item.content_id === this.contentId)
    };
  },
  mounted() {
    console.log("받은데이터", this.CommentList);
  },
};
</script>
