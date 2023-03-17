<template>
  <div id="root">
    <div id="mypage-root">
      <CsceterNav />
      <div class="container">
        <div>
          <div>
            <h2>공지사항</h2>
          </div>
          <v-table class="table-box">
            <thead>
              <tr>
                <th>제목</th>
                <td>제목입니당</td>
              </tr>
              <tr>
                <th>내용</th>
                <td class="content">내용입니다</td>
              </tr>
            </thead>
          </v-table>
          <!-- 관리자만 보이게 설정해야함 -->
          <div>
            <v-btn color="#eee">수정</v-btn>
            <v-btn color="#eee">삭제</v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";

export default {
  components: { CsceterNav },
  data() {
    return {
      writer: "", // 작성자
      title: "", // 글 제목
      createdAt: "", // 작성일
      updatedAt: "", // 최근 수정일
      text: "", // 글 내용
      editable: false, // 수정가능여부 (수정 버튼누르면 true로 바뀜)
    };
  },
  // mounted() {
  //   axios({
  //     url: "http://127.0.0.1:52273/content/content/",
  //     method: "POST",
  //     data: {
  //       id: this.$route.query.id,
  //     },
  //   })
  //     .then((res) => {
  //       this.writer = res.data.writer;
  //       this.title = res.data.title;
  //       this.createdAt = res.data.createdAt.split("T")[0];
  //       this.updatedAt = res.data.updatedAt.split("T")[0];
  //       this.text = res.data.text;
  //     })
  //     .catch((err) => {
  //       alert(err);
  //     });
  // },
  // methods: {
  //   moveback() {
  //     window.history.back(); // window.history.back()을 통해 뒤로가기
  //   },
  //   deletecontent() {
  //     // 글에 들어가서 삭제버튼 눌렀을 때
  //     axios({
  //       url: "http://127.0.0.1:52273/content/delete/",
  //       method: "POST",
  //       data: {
  //         id: this.$route.query.id,
  //       },
  //     })
  //       .then((res) => {
  //         alert(res.data.message);
  //         window.location.href =
  //           window.location.pathname.slice(0, -8) + "/?page=1";
  //         // 삭제 후 그 게시판의 1페이지로 이동
  //       })
  //       .catch((err) => {
  //         alert(err);
  //       });
  //   },
  //   editcontent() {
  //     this.editable = true;
  //   },
  //   editcontentfinish() {
  //     // 수정완료 버튼을 눌렀을 때, 수정된 내용이 저장되야 되기 때문에 back서버와 통신 필요
  //     axios({
  //       url: "http://127.0.0.1:52273/content/edit/",
  //       method: "POST",
  //       data: {
  //         id: this.$route.query.id,
  //         text: this.text,
  //       },
  //     })
  //       .then((res) => {
  //         alert(res.data.message);
  //         this.editable = false;
  //       })
  //       .catch((err) => {
  //         alert(err);
  //       });
  //   },
  //   movetomain() {
  //     window.location.href = "/";
  //   },
  // },
};
</script>

<style lang="scss" scoped>
#root {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
#mypage-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    & > div:nth-child(1) {
      width: 100%;
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}
.table-box {
  border-top: 1px solid #343434;
  th,
  td {
    border-bottom: 1px solid #eee;
    padding: 10px 10px;
    text-align: left;
  }
}
.content {
  height: 300px;
  vertical-align: top;
}
button {
  margin: 10px;
}
</style>
