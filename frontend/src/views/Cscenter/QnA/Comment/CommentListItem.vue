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
      <div>
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
      edit: false,
      anos: "",
    };
  },
  mounted() {
    this.getQnACommentList();
    this.userCheck;
    console.log(this.commentList.ano);
    console.log(this.$store.state.sessionStorageData.mno);
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
      console.log(this.edit);
      if (!this.edit) {
        // console.log("댓글수정");
        const editData = {
          no: this.$route.params.no,
          ano: ano,
          comment: comment,
          aid: this.$store.state.sessionStorageData.mno,
        };
        // console.log(editData);
        console.log(this.commentList.ano);

        try {
          const res = await this.$axios({
            headers: {
              "Content-Type": "application/json",
            },
            method: "POST",
            url: `${process.env.VUE_APP_API_URL}/qnaAnswerUpdate`,
            data: editData,
          });
          if (res.data.data) {
            alert("댓글 수정이 완료되었습니다!");
            window.location.reload();
          } else {
            alert("댓글 수정을 할 수 없습니다!");
          }
          console.log(res);
        } catch (error) {
          console.log(error);
        }
      } else {
        console.log("if문");
      }
    },

    async getQnACommentList() {
      // console.log("댓글가져오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/qnaAnswerList?qno=${this.$route.params.no}`,
        });
        this.commentList = res.data.data;
        console.log(res.data.data);
        console.log(this.commentList.ano);
      } catch (error) {
        console.log(error);
      }
    },
    async deleteComment(no, ano, aid) {
      console.log(ano);
      const deleteData = {
        no: no,
        ano: ano,
        aid: aid,
      };
      console.log(deleteData);
      try {
        const res = await this.$axios({
          headers: {
            "Content-Type": "application/json",
          },
          method: "GET",
          url: `${process.env.VUE_APP_API_URL}/qnaAnswerDelete`,
          params: deleteData,
        });
        console.log(res);
        if (res.data.data) {
          alert("댓글이 삭제되었습니다!");
          this.$router.push({
            name: "csfaq",
          });
        } else {
          alert("댓글을 삭제할 수 없습니다!");
        }
        window.location.reload();
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
