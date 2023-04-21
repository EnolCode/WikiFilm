import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "home",
			component: HomeView,
		},
		{
			path: "/register",
			name: "register",
			component: () => import("../views/RegisterView.vue"),
		},
		{
			path: "/login",
			name: "login",
			component: () => import("../views/LoginView.vue"),
		},
		{
			path: "/dashboard",
			name: "dashboard",
			component: () => import("../views/DashboardView.vue"),
		},
		{
			path: "/addFilm",
			name: "addFilm",
			component: () => import("../views/AddFilmView.vue"),
		},
		{
			path: "/myList",
			name: "MyList",
			component: () => import("../views/MyListView.vue"),
		},
		{
			path: "/films",
			name: "films",
			component: () => import("../views/FilmsView.vue"),
		},{
			path: "/shows",
			name: "shows",
			component: () => import("../views/ShowsView.vue"),
		},
	],
});

export default router;
