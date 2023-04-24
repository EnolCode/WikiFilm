<script setup>
	import { ref, reactive, onBeforeMount, computed } from "vue";
	import CardFilm from "@/components/CardFilm.vue";
	import UserService from "@/services/UserService.js";
	import HeaderUser from "@/components/layout/HeaderUser.vue";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useShowStore } from "@/stores/ShowStore.js";
	import { useAuthStore } from "@/stores/authStore";

	const storeFilms = useFilmStore();
	const storeShows = useShowStore();
	const auth = useAuthStore();
	let watchList = ref([]);
	let searchFilms = ref("");
	const userService = new UserService();
	let titlesInList = ref([]);

	onBeforeMount(async () => {
		await userService.fetchWatchList(auth.username);
		watchList.value = userService.getWatchList();
		const shows = await storeShows.getWatchList();
		const films = await storeFilms.getWatchList();
		titlesInList.value = [...films, ...shows]
	});

	const filteredFilmForTitle = computed(() => {
		if (!searchFilms.value) return titlesInList.value;
		return titlesInList.value.filter(film =>
			film.title
				.toLowerCase()
				.includes(searchFilms.value.toLowerCase())
		);
	});
</script>
<template>
	<HeaderUser
		v-model="searchFilms"
		@update:modelValue="searchFilms = $event"
	/>
	<main class="main">
		<CardFilm
			v-for="film in filteredFilmForTitle"
			:film="film"
		/>
	</main>
</template>
<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.main {
		display: grid;
		width: 80%;
		margin: 0 auto;
		gap: 1em;
		grid-template-columns: repeat(3, 1fr);
		margin-top: 1em;
	}
</style>
