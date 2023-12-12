export default {
  methods: {
    checkObject(object) {
      if (object === null || typeof object !== "object") {
        return object;
      }
    },
    // 깊은 복사
    clone(object) {
      const copy = Array.isArray(object) ? [] : {};
      for (let key of Object.keys(object)) {
        copy[key] = this.checkObject(object[key]);
      }
      return copy;
    },
  },
};
