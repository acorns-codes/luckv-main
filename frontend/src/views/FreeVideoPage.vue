<template>
  <div id="root">
    <div id="info-box">
      <h2 class="test_obj hover-event">Free Video</h2>
    </div>
    <div id="page-root">
      <VideoCategory :type="type" :category="category" />
      <VideoList :videoList="list" @getVideoList="getVideoList" />
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
        kind: "무료",
        vcate: "all",
      },
      list: [],
      type: "freevideoList",
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
  computed: {
    cate() {
      return this.$route.params.type;
    },
  },
  watch: {
    cate: {
      async handler(cate) {
        this.reqModel.vcate = cate;
        await this.getVideoList();
      },
      immediate: true,
    },
    pageInfo: {
      handler(info) {
        this.$router.push({
          name: "freevideoList",
          params: { type: this.cate },
          query: { page: info.page },
        });
      },
      deep: true,
    },
  },
  methods: {
    // 무료동영상 목록 불러오기
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

#info-box {
  width: 100%;
  height: 500px;
  background-image: url("https://img.freepik.com/free-vector/hand-drawn-minimal-background_23-2149017896.jpg?w=740&t=st=1680747133~exp=1680747733~hmac=76144e94899ed69380459068e9a191ed239d58ce3302d8fbc7095a3a3d9c0686");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  & > h2 {
    font-family: "ghanachoco";
    font-size: 5rem;
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
