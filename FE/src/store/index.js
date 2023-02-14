import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  plugins: [createPersistedState()],

  state: {
    user: null,
  },
  getters: {},
  mutations: {
    LOGIN(state, userInfo) {
      state.user = userInfo;
    },
    LOGOUT(state) {
      state.user = "";
    },
    GET_STUDIO_COUNT(state, studioCount) {
      state.user.myStudioCount = studioCount;
    },
  },
  actions: {
    login({ commit }, userInfo) {
      commit("LOGIN", userInfo);
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
    getStudioCount({ commit }, studioCount) {
      commit("GET_STUDIO_COUNT", studioCount);
    },
  },
  modules: {},
});
