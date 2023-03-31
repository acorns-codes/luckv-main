<template>
  <div>
    <HeaderLayout />
    <slot />
    <div>
      <button>이용권구매</button>
    </div>
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

</style>
