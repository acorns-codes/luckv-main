<template>
  <div class="button-box">
    <button
      v-for="(item, index) in categorys"
      :key="index"
      @click="clickCategory(item.value)"
    >
      {{ item.title }}
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      categorys: [
        { title: "ALL", value: "all" },
        {
          title: "동물",
          value: "animal",
        },
        {
          title: "인물",
          value: "character",
        },
        {
          title: "건물",
          value: "building",
        },
        {
          title: "식물",
          value: "plant",
        },
        {
          title: "기타",
          value: "etc",
        },
      ],
    };
  },
  methods: {
    clickCategory(category) {
      //   console.log(category);
      this.$router.push({
        name: category,
      });
      //   console.log(this.$route.name);
    },
  },
};
</script>

<style lang="scss" scoped>
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
