/* eslint-disable import/no-extraneous-dependencies */
import { createApp } from "vue";
import { vfmPlugin } from "vue-final-modal";
import App from "./App.vue";
import router from "./router";
import store from "./store";

createApp(App).use(store).use(router).use(vfmPlugin).mount("#app");
