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
  },
  actions: {
    login({ commit }, userInfo) {
      commit("LOGIN", userInfo);
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
  },
  modules: {},
});
