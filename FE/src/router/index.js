import { createRouter, createWebHistory } from "vue-router";
import { getStudioInfo } from "@/api/studio";
import store from "@/store/index";
import MainView from "@/views/MainView.vue";
import FilmView from "@/views/FilmView.vue";
import LoginView from "@/views/LogInView.vue";
import NavFooterView from "@/views/NavFooterView.vue";
import StudioView from "@/views/StudioView.vue";
import SearchView from "@/views/SearchView.vue";
import PieceDetailView from "@/views/PieceDetailView.vue";
import StoryView from "@/views/StoryView.vue";
import ProfileView from "@/views/ProfileView.vue";

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
        path: "/story/:story_id",
        name: "story",
        component: StoryView,
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
            path: "/search/category/:categoryId/menu/:menuId",
            name: "search-group",
            component: SearchView,
            children: [
              {
                path: "/search/category/:categoryId/menu/:menuId/keyword/:keyword/",
                name: "search-result",
                component: SearchView,
                children: [
                  {
                    path: "/search/category/:categoryId/menu/:menuId/keyword/:keyword/page/:page",
                    name: "search-result-page",
                    component: SearchView,
                  },
                ],
              },
              {
                path: "/search/category/:categoryId/menu/:menuId/page/:page",
                name: "search-group-page",
                component: SearchView,
              },
            ],
          },
        ],
      },
      {
        path: "/piece/:pieceId",
        name: "piece",
        component: PieceDetailView,
      },
      {
        path: "/profile/:userId",
        name: "profile",
        component: ProfileView,
        children: [
          {
            path: "/profile/:userId/studios",
            name: "profile-studios",
            component: ProfileView,
          },
          {
            path: "/profile/:userId/films",
            name: "profile-films",
            component: ProfileView,
          },
          {
            path: "/profile/:userId/boards",
            name: "profile-boards",
            component: ProfileView,
          },
          {
            path: "/profile/:userId/comments",
            name: "profile-comments",
            component: ProfileView,
          },
        ],
      },
    ],
  },
  {
    path: "/studio/:studioId",
    name: "studio",
    component: StudioView,
    beforeEnter: (to, from, next) => {
      try {
        let members = [];
        getStudioInfo(
          to.params.studioId,
          ({ data }) => {
            members = data.member_list;
            const isMember = members.some((member) => member.user_id === store.state.user.userId);
            if (isMember) {
              next();
            } else {
              // eslint-disable-next-line no-alert
              alert("스튜디오에 초대된 회원만 입장하실 수 있습니다.");
              next(false);
            }
          },
          (error) => {
            console.log(error);
          }
        );
      } catch (error) {
        console.log(error);
        next(false);
      }
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior(to, from) {
    if (
      to.matched[to.matched.length - 1]?.components.default ===
      from.matched[from.matched.length - 1]?.components.default
    ) {
      return null;
    }
    return { top: 0 };
  },
});

export default router;
