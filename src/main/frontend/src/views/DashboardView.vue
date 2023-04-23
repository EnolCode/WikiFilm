<script setup>
	import {
		ref,
		reactive,
		onBeforeMount,
		computed,
		onMounted,
	} from "vue";
	import HeaderUser from "@/components/layout/HeaderUser.vue";
	import FootPage from "@/components/layout/FootPage.vue";
	import CardFilm from "@/components/CardFilm.vue";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useShowStore } from "@/stores/ShowStore.js";
	import CopyRight from "@/components/CopyRight.vue";
	import FilmService from "@/services/FilmService.js";
	import ShowService from "@/services/ShowService.js";

	const storeFilms = useFilmStore();
	const storeShows = useShowStore();
	let searchTitles = ref("");
	const service = new FilmService();
	let titles = ref([]);
	const sortedItemsByRating = computed(() => {
		return filteredFilmForTitle.value.sort(
			(a, b) => b.rating - a.rating
		);
	});

	

	onMounted(async () => {
		const films = await storeFilms.getAllFilmsForRating();
		const shows = await storeShows.getAllShowsForRating();
		titles.value = [...films, ...shows];
	});

	const filteredFilmForTitle = computed(() => {
		if (!searchTitles.value) return titles.value;
		return titles.value.filter(titles =>
			titles.title
				.toLowerCase()
				.includes(searchTitles.value.toLowerCase())
		);
	});
</script>

<template>
	<HeaderUser
		v-model="searchTitles"
		@update:modelValue="searchTitles = $event"
	/>
	<main>
		<CardFilm
			class="card"
			v-for="film in sortedItemsByRating"
			:film="film"
		/>
	</main>
	<FootPage />
	<CopyRight class="copyright" />
</template>

<style lang="scss">
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	main {
		display: grid;
		background: map-get(c.$colors, "black");
		width: 80%;
		gap: 1em;
		margin: 0 auto;
		margin-top: 1em;
		grid-template-columns: repeat(6, 1fr);
		grid-auto-rows: minmax(5em, auto);
	}
	.card {
		grid-column: span 2;
	}
	.card:first-child,
	.card:nth-child(2) {
		grid-column: span 3;
	}

	.copyright {
		margin-left: 1.22em;
	}
</style>
