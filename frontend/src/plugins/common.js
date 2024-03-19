import { apiGetInfoMember } from "@/api/user";
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
    async refreshUserInfo() {
      console.log("refresh");
      const req = {
        mno: this.sessionStorageData.mno,
      };
      try {
        const res = await apiGetInfoMember(req);
        if (res) {
          console.log(res, "resfresh");
          this.$store.commit("setSessionStorage", JSON.stringify(res.data));
          this.$store.commit(
            "readSessionStorage",
            this.$store.state.sessionStorageData
          );
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  computed: {
    sessionStorageData() {
      return this.$store.state.sessionStorageData;
    },
  },
};
