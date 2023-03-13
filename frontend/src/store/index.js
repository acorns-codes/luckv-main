import { createStore } from "vuex";

export default createStore({
  state: {
    userId: "",
    isClicked: false,
    accessToken: "",
  },
  getters: {
    isLogin(state) {
      return state.userId !== "";
    },
    isSignUp(state) {
      return state.signUp;
    },
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
    },
    clearUserId(state) {
      state.userId = "";
    },
  },
});
