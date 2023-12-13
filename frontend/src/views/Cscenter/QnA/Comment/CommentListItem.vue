<template>
  <div id="root">
    <div class="comment-box" v-for="item in commentList" :key="item.ano">
      <div>
        <v-text-field
          v-if="edit && item.ano == this.anos"
          v-model="item.comment"
          variant="underlined"
        >
        </v-text-field>
        <p v-else>{{ item.comment }}</p>
        <p>{{ item.acreate }}</p>
      </div>

      <!-- 작성자만 수정 삭제 버튼 보일수 있게 수정  -->
      <div v-if="this.$store.state.sessionStorageData.auth === 'A'">
        <v-btn
          variant="plain"
          size="x-small"
          @click="clickEdit(item.ano, item.comment)"
          >수정</v-btn
        >
        <v-btn
          variant="plain"
          size="x-small"
          @click="deleteComment(item.no, item.ano, item.aid)"
          >삭제</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
import { apiEditAnswer, apiDeleteQnaAnswer } from "@/api/qna";
export default {
  name: "PrCommentListItem",
  props: {
    commentList: Object,
    commentItem: Object,
    reload: Function,
  },
  data() {
    return {
      name: "",
      edit: false,
      anos: "",
    };
  },
  mounted() {
    this.userCheck;
  },
  methods: {
    userCheck(no) {
      if (no === this.$store.state.sessionStorageData.mno) {
        return;
      }
    },

    async clickEdit(ano, comment) {
      this.anos = ano;
      this.edit = !this.edit;
      if (!this.edit) {
        // console.log("댓글수정");
        const editData = {
          no: this.$route.params.no,
          ano: ano,
          comment: comment,
          aid: this.$store.state.sessionStorageData.mno,
        };
        // console.log(editData);

        try {
          const res = await apiEditAnswer(editData);
          if (res) {
            alert("댓글 수정이 완료되었습니다!");
            this.$emit("getQnACommentList");
          }
        } catch (error) {
          console.error(error);
        }
      }
    },

    async deleteComment(no, ano, aid) {
      const deleteData = {
        no: no,
        ano: ano,
        aid: aid,
      };
      try {
        const res = await apiDeleteQnaAnswer(deleteData);
        if (res) {
          alert("댓글이 삭제되었습니다!");
          this.$emit("getQnACommentList");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.comment-box {
  display: flex;
  padding: 15px;
  border-bottom: 1px solid #eee;

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
