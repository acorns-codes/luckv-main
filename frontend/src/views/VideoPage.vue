<template>
  <div id="root">
    <div id="page-root">
      <div class="button-box">
        <button
          v-for="(item, index) in categorys"
          :key="index"
          @click="this.video(item.url)"
        >
          {{ item.title }}
        </button>
      </div>
      <VideoList :videoList="this.videoList" />
    </div>
  </div>
</template>

<script>
import VideoList from "@/views/videoPage/VideoList.vue";
export default {
  components: { VideoList },
  data() {
    return {
      videoList: "",
      categorys: [
        { title: "ALL", value: "", url: "auctionAll" },
        {
          title: "동물",
          value: "animal",
          url: "auctionAll?vcate=animal",
        },
        {
          title: "인물",
          value: "character",
          url: "auctionAll?vcate=character",
        },
        {
          title: "건물",
          value: "building",
          url: "auctionAll?vcate=building",
        },
        {
          title: "식물",
          value: "plant",
          url: "auctionAll?vcate=plant",
        },
        {
          title: "기타",
          value: "etc",
          url: "auctionAll?vcate=etc",
        },
      ],
    };
  },

  mounted() {
    this.video("auctionAll");
  },
  methods: {
    // 비디오 리스트 받아오기
    async video(category) {
      console.log("비디오");
      try {
        const res = await this.$axios({
          methods: "GET",
          url: `${process.env.VUE_APP_API_URL}/${category}`,
        });
        this.videoList = res.data.data;
        console.log(this.videoList);
      } catch (e) {
        console.log(e);
      }
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
  display: flex;
  flex-direction: column;
  align-items: center;
}

.button-box {
  width: 400px;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  & > button {
    color: #343434;
    background-color: #f4f4f4;
    border-radius: 10px;
    padding: 2px 10px 2px 10px;
  }
}
</style>
