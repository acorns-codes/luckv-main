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
      <!-- <VideoCategory /> -->
      <VideoList :videoList="this.videoList" />
    </div>
  </div>
</template>

<script>
// import VideoCategory from "@/components/VideoCategory.vue";
import VideoList from "@/views/videoPage/VideoList.vue";
export default {
  components: { VideoList },

  data() {
    return {
      src: "",
      // videosrc: `${process.env.VUE_APP_API_URL}/videoplay?ano=${this.videoList.ano}`,
      show: false,
      mouseover: false,
      videoList: "",
      num: "",
      videoSrc: "",
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
    this.videoSrc = process.env.VUE_APP_API_URL;
  },
  methods: {
    clickCategory(category) {
      console.log(category);
      // this.$router.push({
      //   name: category,
      // });
      //   console.log(this.$route.name);
    },
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
    // evnetAdd() {
    //   this.mouseover = !this.mouseover;
    // },
    showbox(ano) {
      console.log(this.num);
      this.num = ano;
      this.show = !this.show;
      console.log(ano);
      console.log(this.num);
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
span {
  color: #ff5e5e;
  font-weight: bolder;
}
.pay-box {
  display: flex;
  justify-content: space-between;
  padding: 0 20px 5px 20px;
  & p {
    width: 90px;
    font-size: 0.9rem;
  }
  & > p:nth-child(1) {
    width: 60px;
    background-color: #343434;
    border-radius: 5px;
    color: white;
  }
}

.video-box {
  width: 344px;
  height: 200px;
}
video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card-box {
  width: 960px;
  display: flex;
  flex-wrap: wrap;
  padding: 30px;
  margin: 30px;
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
