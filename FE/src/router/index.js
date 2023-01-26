import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import MainView from '../views/MainView.vue';

const routes = [
  {
    path: '/',
    name: 'MainView',
    component: MainView,
    children : [
      {
        path: '/home',
        component: HomeView
      }
    ]
  },
  {
    path: '/studio',
    name: 'Studio',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
