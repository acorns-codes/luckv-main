import { createStore } from "vuex";

export default createStore({
  state: {
    userId: "",
  },
  getters: {
    isLogin(state) {
      return state.userId !== "";
    },
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
    },
  },
});
