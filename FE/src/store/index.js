import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";

export default createStore({
  plugins: [
    createPersistedState()
  ],

  state: {
    token: "",
  },
  getters: {
  },
  mutations: {
    LOGIN(state, token) {
      state.token = token;
    },
    LOGOUT(state) {
      state.token = "";
    }
  },
  actions: {
    login({ commit }, token) {
      commit("LOGIN", { token })
    },
    logout({commit}) {
      commit("LOGOUT")
    }
  },
  modules: {
  },

  
});
