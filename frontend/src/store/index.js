import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    videoSrc: process.env.VUE_APP_API_URL,
    userId: "",
    isClicked: false,
    accessToken: "",
    sessionStorageData: "",
    userData: "",
    socket: "",
    subAuth: "",
    subData: "",
  },
  getters: {
    // 로그인 확인
    isLogin(state) {
      return state.sessionStorageData !== "";
    },
    checkSubAuth(state) {
      return state.subAuth + "새로저장함";
    },
  },
  mutations: {
    // 로그아웃 클릭 시, sessionStorage 삭제 및 data 삭제
    clearUser(state) {
      sessionStorage.clear("login");
      state.sessionStorageData = "";
    },
    // "login"으로 session에 저장
    setSessionStorage(state, payload) {
      sessionStorage.setItem("login", payload);
    },
    // session storage에 있는 데이터 가져오기
    // 데이터가 존재하면, sessionStorageData에 객체 형태로 저장
    readSessionStorage(state) {
      state.sessionStorageData = JSON.parse(
        sessionStorage.getItem("login", state)
      );
    },
    // 로그인하면 id 저장
    getUserId(state, userId) {
      state.userId = userId;
    },
    // 회원정보 불러와서 UserData에 저장
    getUserData(state, userData) {
      state.userData = userData;
    },
    storeSocket(state, socket) {
      state.socket = socket;
    },
    storeSubAuth(state, subAuth) {
      state.subAuth = subAuth;
    },
  },
  // 새로고침해도 session정보 유지 될 수 있도록 했음
  plugins: [
    createPersistedState({
      paths: ["sessionStorageData"],
    }),
  ],
});
