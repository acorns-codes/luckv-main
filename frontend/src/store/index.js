import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    userId: "",
    isClicked: false,
    accessToken: "",
    sessionStorageData: "",
    userData: "",
  },
  getters: {
    isLogin(state) {
      return state.sessionStorageData !== "";
    },
    // && sessionStorage.clear("login")
    isSignUp(state) {
      return state.signUp;
    },
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
    },
    clearUser(state) {
      console.log("클리어유저");
      sessionStorage.clear("login");
      state.sessionStorageData = "";
    },
    setSessionStorage(state, payload) {
      sessionStorage.setItem("login", payload);
    },
    readSessionStorage(state, payload) {
      if (sessionStorage.getItem("login", payload) != null) {
        state.sessionStorageData = JSON.parse(
          sessionStorage.getItem("login", state)
        );
      }
    },
    getUserData(state, userData) {
      state.userData = userData;
    },
    // getSessionStorage(state) {
    //   state.sessionStorageData = JSON.parse(
    //     sessionStorage.getItem("login", state)
    //   );
    // },
  },

  plugins: [
    createPersistedState({
      paths: ["sessionStorageData"],
    }),
  ],
});
