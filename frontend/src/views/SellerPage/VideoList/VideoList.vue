<template>
  <div class="mypage">
    <div>
      <div class="button-box"></div>
      <VideoItem :auctionList="auctionList" :type="type" />
    </div>
    <!-- 페이징 -->
    <Pagination
      v-if="auctionList.length != 0"
      @goPage="goPage"
      :pageNum="pageInfo.page"
      :pageSize="pageInfo.rowCnt"
      :totalPageCount="pageInfo.totalPageCnt"
    />
  </div>
</template>

<script>
import { apiGetAuctionList } from "@/api/video";
import VideoItem from "./VideoItem.vue";
import Pagination from "@/components/PagiNationVue.vue";

export default {
  components: { VideoItem, Pagination },
  data() {
    return {
      reqModel: {
        seller: 0,
        page: 1,
        rowCnt: 20,
        kind: "경매",
      },
      pageInfo: {},
      auctionList: [],
    };
  },
  computed: {
    type() {
      const name = this.$route.name;
      return name;
    },
  },
  watch: {
    type: {
      async handler(type) {
        this.reqModel.kind = type;
        await this.getVideo();
      },
      immediate: true,
    },
    pageInfo: {
      handler(info) {
        this.$router.push({
          name: this.type,
          query: { page: info.page },
        });
      },
      deep: true,
    },
  },
  methods: {
    async getVideo(page) {
      const req = this.clone(this.reqModel);
      req.seller = this.$store.state.sessionStorageData.mno;
      if (page) {
        req.page = page;
      }
      try {
        const res = await apiGetAuctionList(req);
        this.auctionList = res.list;
        this.pageInfo = res.pageInfo;
      } catch (error) {
        console.error(error);
      }
    },
    async goPage(page) {
      await this.getVideo(page);
    },
  },
};
</script>

<style lang="scss" scoped>
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

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
