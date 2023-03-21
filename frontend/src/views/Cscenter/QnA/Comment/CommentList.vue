<template>
  <div>
    <!-- 댓글 데이터를 불러와 반복문으로 돌려 컴포넌트에 각각 들어가도록 한다.-->
    <!-- <div v-for="item in commentObj" :key="item.comment_id"> -->
    <!-- <PrCommentListItem :commentItem="item" :reload="reload"/> -->
    <CommentListItem />
  </div>
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
    contentId: Number,
  },
  data() {
    return {
      //   commentObj : data.Comment.filter(item => item.content_id === this.contentId)
    };
  },
};
</script>
