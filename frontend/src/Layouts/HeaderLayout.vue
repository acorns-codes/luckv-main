<template>
  <div>
    <HeaderView />
    <slot />
  </div>
</template>

<script>
import HeaderView from "@/components/HeaderView.vue";
export default {
  name: "HeaderLayout",
  components: {
    HeaderView,
  },
};
</script>

<style scoped>
div{width: 100%;}
</style>
