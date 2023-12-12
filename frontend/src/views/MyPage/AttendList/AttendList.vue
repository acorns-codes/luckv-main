<template>
  <div class="mypage">
    <div>
      <AttendItem :attendList="attendList" />
    </div>
    <!-- 페이징 -->
    <Pagination
      v-if="attendList.length != 0"
      @goPage="goPage"
      :pageNum="pageInfo.page"
      :pageSize="pageInfo.rowCnt"
      :totalPageCount="pageInfo.totalPageCnt"
    />
  </div>
</template>

<script>
import { apiGetAttendList } from "@/api/attend";
import AttendItem from "./AttendItem.vue";
import Pagination from "@/components/PagiNationVue.vue";

export default {
  components: { AttendItem, Pagination },
  data() {
    return {
      reqModel: {
        buyer: 0,
        page: 1,
        rowCnt: 20,
      },
      pageInfo: {},
      attendList: [],
    };
  },

  methods: {
    async getList() {
      const req = this.clone(this.reqModel);
      req.buyer = this.$store.state.sessionStorageData.mno;
      try {
        const res = await apiGetAttendList(req);
        console.log(res);
        this.attendList = res.list;
        this.pageInfo = res.pageInfo;
      } catch (error) {
        console.log(error);
      }
    },
    async goPage(page) {
      await this.getList(page);
    },
  },
  async created() {
    await this.getList();
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
@/api/attend
