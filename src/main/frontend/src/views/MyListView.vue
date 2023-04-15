<script setup>
	import { ref, reactive, onBeforeMount, computed } from "vue";
	import CardFilm from "@/components/CardFilm.vue";
	import HeaderUser from "@/components/layout/HeaderUser.vue";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useAuthStore } from "@/stores/authStore";

	const storeFilms = useFilmStore();
	const auth = useAuthStore();
	let watchList = ref([]);
	let searchFilms = ref("");

	onBeforeMount(async () => {
		watchList.value = await storeFilms.getWatchList();
	});

	const filteredFilmForTitle = computed(() => {
		if (!searchFilms.value) return watchList.value;
		return watchList.value.filter(film =>
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
		background: map-get(c.$colors, "black");
		width: 80%;
		margin: 0 auto;
		gap: 1em;
		grid-template-columns: repeat(3, 1fr);
		margin-top: 1em;
	}
</style>
