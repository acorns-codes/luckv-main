<template>
  <div>
    <ul>
      <li>linkV</li>
      <slot name="login" :childData="isForm">
        <li class="login" v-bind:class="{ click: isForm }" @click="setForm">
          로그인
        </li>
      </slot>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isForm: false,
    };
  },
  methods: {
    setForm() {
      this.isForm = !this.isForm;
      this.$emit("child", this.isForm);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/styles/sass/variables.scss";
div {
  color: $col-1;
  & > ul {
    /* @include display(space-between,center); */
    list-style-type: none;
    height: 100px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 50px 0 50px;
    & > li {
      cursor: pointer;
    }
  }
}
.login:hover {
  color: white;
  background-color: $col-1;
  padding: 3px;
}
</style>
