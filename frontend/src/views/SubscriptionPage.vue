<template>
  <div id="root">
    <div id="info-box">
      <h2 class="test_obj hover-event">Subscription Video</h2>
      <button @click="sub">JOIN UP FOR MEMBERSHIP</button>
    </div>
    <div id="page-root">
      <VideoCategory :type="type" :category="category" />
      <VideoList :videoList="list" />
      <Pagination
        v-if="list.length != 0"
        @goPage="goPage"
        :pageNum="pageInfo.page"
        :pageSize="pageInfo.rowCnt"
        :totalPageCount="pageInfo.totalPageCnt"
      />
    </div>
  </div>
</template>

<script>
import { apiGetAuctionAll } from "@/api/video";
import { apiPostVideo } from "@/api/user";
import VideoList from "@/components/video/VideoList.vue";
import VideoCategory from "@/components/video/VideoCategory.vue";
import Pagination from "@/components/PagiNationVue.vue";

export default {
  components: { VideoList, VideoCategory, Pagination },
  data() {
    return {
      reqModel: {
        page: 1,
        rowCnt: 20,
        kind: "구독",
        vcate: "all",
      },
      list: [],
      type: "subscriptionList",
      category: [
        {
          name: "ALL",
          type: "all",
        },
        {
          name: "동물",
          type: "animal",
        },
        {
          name: "인물",
          type: "character",
        },
        {
          name: "건물",
          type: "building",
        },
        {
          name: "식물",
          type: "plant",
        },
        {
          name: "기타",
          type: "etc",
        },
      ],
    };
  },
  watch: {
    $route: {
      handler(route) {
        const type = route.params.type;
        this.reqModel.vcate = type;
      },
      immediate: true,
    },
    "reqModel.vcate": {
      async handler() {
        await this.getVideoList();
      },
    },
  },
  methods: {
    // 구독자 확인
    subUser() {
      // console.log(this.$store.state.sessionStorageData.subYn);
      if (this.$store.state.sessionStorageData.subYn !== "Y") {
        alert("구독 회원을 위한 영상입니다! 사용을 원하시면 신청을 해주세요!");
      }
    },
    // 구독 동영상 목록 불러오기
    async getVideoList(page) {
      const req = this.clone(this.reqModel);
      if (page) {
        req.page = page;
      }
      if (req.vcate == "all") {
        delete req.vcate;
      }
      try {
        const res = await apiGetAuctionAll(req);
        this.list = res.list;
        this.pageInfo = res.pageInfo;
      } catch (e) {
        console.log(e);
      }
    },
    async goPage(page) {
      await this.getVideoList(page);
    },
    // 구독신청
    async sub() {
      console.log(this.$store.state.subAuth);
      if (this.$store.state.subAuth === "Y") {
        alert("이미 구독중입니다!");
      } else if (this.$store.state.sessionStorageData.auth !== "B") {
        alert("구매자 회원만 구독이 가능합니다!");
      } else {
        if (!confirm("구독을 신청하시겠습니까?")) {
          alert("구독 신청이 완료되지 못했습니다!");
        } else {
          const req = {
            mno: this.$store.state.sessionStorageData.mno,
          };
          try {
            const res = await apiPostVideo(req);
            console.log(res);
            if (res.data) {
              alert("구독 신청이 완료되었습니다!");
              this.$store.commit("storeSubAuth", "Y");
            }
          } catch (error) {
            console.log(error);
          }
        }
      }
    },
  },
  async created() {
    // 비디오 목록 받아오는 함수 실행
    await this.getVideoList();
  },
};
</script>
<style lang="scss" scoped>
#root {
  padding-top: 100px;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

#page-root {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.sub-box {
  width: inherit;
  height: 500px;
  border: 1px solid black;
}
#info-box {
  width: 100%;
  height: 500px;
  background-image: url("https://img.freepik.com/free-vector/background-seamless-pattern-vector-with-cute-memphis_53876-105506.jpg?w=1380&t=st=1680682686~exp=1680683286~hmac=49cd1539c8c42f4866a0f4e4d5f60c229160fb12995a949caf2a77b26592d1e2");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  & > h2 {
    font-family: "ghanachoco";
    font-size: 5rem;
  }
  & > button {
    color: #ffb22d;
    font-weight: bold;
    font-size: 1.3rem;
    letter-spacing: 5px;
  }
  & > button:hover {
    text-decoration: underline;
  }
}
@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translate3d(0, 100%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}
.test_obj {
  position: relative;
  animation: fadeInUp 1s;
}
.hover-event {
  --duration: 0.44s;
  --move-hover: -4px;
  transform: translateY(var(--y)) translateZ(0);
  transition: transform var(--duration) ease, box-shadow var(--duration) ease;
  &:hover {
    --y: var(--move-hover);
    --shadow: var(--shadow-hover);
    span {
      --m: calc(var(--font-size) * -1);
    }
  }
}
</style>
