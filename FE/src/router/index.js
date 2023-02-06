import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView.vue";
import FilmView from "@/views/FilmView.vue";
import LoginView from "@/views/LogInView.vue";
import NavFooterView from "@/views/NavFooterView.vue";
import StudioView from "@/views/StudioView.vue";
import SearchView from "@/views/SearchView.vue";
// import SearchResult from "@/components/search/SearchResult.vue";

const routes = [
  {
    path: "/",
    name: "navFooter",
    component: NavFooterView,
    children: [
      {
        path: "/",
        name: "main",
        component: MainView,
      },
      {
        path: "/film",
        name: "film",
        component: FilmView,
      },
      {
        path: "/login",
        name: "login",
        component: LoginView,
      },
      {
        path: "/search",
        name: "search",
        component: SearchView,
        children: [
          {
            path: "/search/:keyword",
            name: "search-result",
            component: () => import("@/components/search/SearchResult.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/studio",
    name: "studio",
    component: StudioView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
