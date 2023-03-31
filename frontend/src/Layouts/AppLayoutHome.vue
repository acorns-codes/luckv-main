<template>
  <button class="sub-btn-box"></button>
  <div>
    <HeaderLayout />
    <slot />
  </div>
</template>

<script>
import HeaderLayout from "./HeaderLayout.vue";
export default {
  name: "AppLayoutHome",
  components: { HeaderLayout },
};
</script>

<style scoped>
.sub-btn-box {
  width: 70px;
  height: 70px;
  background-color: #434343;
  position: fixed;
}
</style>
