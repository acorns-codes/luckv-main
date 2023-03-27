<template>
  <div id="root">
    <div id="page-root">
      <VideoCategory />
      <div>
        <h1>Etc</h1>
      </div>
    </div>
  </div>
</template>

<script>
import VideoCategory from "@/components/VideoCategory.vue";
export default {
  components: { VideoCategory },
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
</style>
