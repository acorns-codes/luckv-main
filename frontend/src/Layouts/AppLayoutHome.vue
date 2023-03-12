<template>
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

<style scoped></style>
