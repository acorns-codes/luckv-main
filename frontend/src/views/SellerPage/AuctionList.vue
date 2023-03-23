<template>
  <div id="root">
    <div id="page-root">
      <MypageNav />
      <div class="mypage">
        <div>
          <div>
            <h2>경매 판매내역</h2>
          </div>
          <v-table>
            <thead>
              <tr>
                <th
                  v-for="(item, index) in headers"
                  :key="index"
                  class="text-center"
                >
                  {{ item.value }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(item, index) in auctionList"
                :key="index"
                class="event"
                @click="auctionDetail(item.ano)"
              >
                <td>{{ index + 1 }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.payStart }}</td>
                <td>{{ item.payMax }}</td>
                <td>{{ item.status }}</td>
              </tr>
            </tbody>
          </v-table>

          <v-btn color="success" @click="postAuction">
            경매 내역 추가하기
          </v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MypageNav from "@/components/MypageNav.vue";
export default {
  components: { MypageNav },
  data() {
    return {
      auctionList: "",
      itemsPerPage: 5,
      headers: [
        { title: "index", value: "번호" },
        { title: "title", value: "제목" },
        { title: "content", value: "시작가" },
        { title: "payMax", value: "최고가" },
        { title: "status", value: "진행사항" },
      ],
    };
  },
  mounted() {
    this.getAuction(1);
  },
  methods: {
    registration() {
      console.log("상품등록");
      const validate = this.$refs.form.validate();
      if (validate) {
        if (confirm("새로운 경매를 등록하시겠습니까?")) {
          const videoData = {
            title: this.title,
            content: this.content,
            payStart: this.payStart,
            startDay: this.startDay,
            startTime: this.startTime,
            lastDay: this.lastDay,
            lastTime: this.lastTime,
          };
          console.log(videoData);
        }
      }
    },
    async getAuction(page) {
      console.log("공지사항 불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://ec2-3-36-88-52.ap-northeast-2.compute.amazonaws.com:80/auctionPage?seller=${this.$store.state.sessionStorageData.mno}?page=${page}`,
        });
        console.log(res.data);
        this.auctionList = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    postAuction() {
      this.$router.push({
        name: "/postauction",
      });
    },
    auctionDetail(ano) {
      console.log(ano);
      console.log("상세");
      this.$router.push({
        name: "auctionDetail",
        params: { ano: ano },
      });
    },
  },
};
</script>
<style lang="scss" scoped>
#root {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
#page-root {
  width: 1440px;
  height: auto;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}
.mypage {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding-top: 110px;
  & > div {
    width: 100%;
    & > div:nth-child(1) {
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}

button {
  margin: 30px;
}

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
