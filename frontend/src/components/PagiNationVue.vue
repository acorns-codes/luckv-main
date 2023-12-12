<template>
  <div class="pagination-container">
    <ul class="pagination-wrap">
      <li @click="goPage(1)" class="arrow" v-if="pageNum !== 1">
        <button type="button" class="paging">
          <v-icon> mdi-chevron-double-left </v-icon>
        </button>
      </li>
      <li @click="goPage(pageNum - 1)" class="arrow" v-if="pageNum !== 1">
        <button type="button " class="paging">
          <v-icon> mdi-chevron-left </v-icon>
        </button>
      </li>
      <li v-else>
        <button type="button">
          <v-icon> mdi-chevron-left </v-icon>
        </button>
      </li>
      <template v-for="i in navArray" :key="`nav${i}`">
        <li @click="goPage(i)">
          <button
            :class="{ active: pageNum === i }"
            type="button"
            class="paging"
          >
            {{ i < 10 ? "0" + i : i }}
          </button>
        </li>
      </template>
      <li
        @click="goPage(pageNum + 1)"
        class="arrow"
        v-if="!(navMax <= pageNum)"
      >
        <button type="button" class="paging">
          <v-icon> mdi-chevron-right </v-icon>
        </button>
      </li>
      <li v-else>
        <button type="button" class="paging">
          <v-icon> mdi-chevron-right </v-icon>
        </button>
      </li>
      <li @click="goPage(navMax)" class="arrow" v-if="!(navMax <= pageNum)">
        <button type="button" class="paging">
          <v-icon> mdi-chevron-double-right </v-icon>
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "paginationVue",
  emits: ["goPage"],
  props: {
    pageNum: {
      type: Number,
      default: 1,
      required: true,
    },
    pageSize: {
      type: Number,
      default: 20,
      required: true,
    },
    totalCount: {
      //list Count
      type: Number,
      default: 0,
      required: false,
    },
    totalPageCount: {
      //page Count
      type: Number,
      default: 0,
      required: true,
    },
    navSize: {
      // 한 페이지 당 출력 개수
      type: Number,
      default: 5,
    },
  },
  computed: {
    navArray() {
      return new Array(Math.max(this.navMin, this.navEnd - this.navStart + 1))
        .fill(this.navStart)
        .map((n, i) => n + i);
    },
    navStart() {
      return Math.max(
        this.pageNum -
          (this.navSize -
            1 -
            Math.min(Math.floor(this.navSize / 2), this.navMax - this.pageNum)),
        this.navMin
      );
    },
    navEnd() {
      return Math.min(
        this.pageNum +
          Math.max(Math.floor(this.navSize / 2), this.navSize - this.pageNum),
        this.navMax
      );
    },
    navMin() {
      return 1;
    },
    navMax() {
      let pageCnt;
      if (this.totalPageCount && this.totalCount) {
        pageCnt = this.totalPageCount;
      } else if (this.totalPageCount && !this.totalCount) {
        pageCnt = this.totalPageCount;
      } else if (!this.totalPageCount && this.totalCount) {
        pageCnt = Math.floor((this.totalCount - 1) / this.pageSize) + 1;
      }
      return pageCnt;
    },
  },
  methods: {
    goPage(no) {
      this.$emit("goPage", no);
    },
  },
};
</script>

<style scoped>
.pagination-container {
  margin: 20px;
}
.pagination-wrap {
  display: flex;
  width: fit-content;
  margin: 0 auto;
  overflow: hidden;
  gap: 3px;
  padding: 10px;
  align-items: center;
}
.pagination-wrap > li {
  display: flex;
  align-items: center;
}
.pagination-wrap button {
  min-width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  background: #fff;
  color: #dfe0e6;
  user-select: none;
  font-weight: 700;
}

.pagination-wrap .paging {
  cursor: pointer;
}
.pagination-wrap button > i {
  color: #dfe0e6;
}
.pagination-wrap .paging:hover > i {
  color: #6a6a6a;
}
.pagination-wrap .paging:hover:not(.active) {
  color: #6a6a6a;
  background-color: #c8c8c86c;
  border-radius: 100%;
}
.pagination-wrap li.arrow:first-child button,
.pagination-wrap li.arrow button {
  color: #484848;
}
.pagination-wrap button.active {
  color: #ffffff;
  background-color: #ff9103;
  border-radius: 100%;
}

/* .pagination-wrap button:hover:not(.active) {
  color: #484848;
} */
</style>
