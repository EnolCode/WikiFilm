<script setup>
	import { ref, onBeforeMount, computed } from "vue";
	import HeaderUser from "@/components/layout/HeaderUser.vue";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import CardFilm from "@/components/CardFilm.vue";
	import FootPage from "@/components/layout/FootPage.vue";
	import CopyRight from "@/components/CopyRight.vue";

	const storeFilms = useFilmStore();
	let films = ref([]);
	let searchFilms = ref("");

	onBeforeMount(async () => {
		films.value = await storeFilms.getAllFilmsForRating();
	});

	const deleteFilm = id => {
		films.value = films.value.filter(film => film.id !== id);
		service.deleteFilm(id);
	};

	const filteredFilmForTitle = computed(() => {
		if (!searchFilms.value) return films.value;
		return films.value.filter(film =>
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
	<main>
		<CardFilm
			class="card"
			v-for="film in films"
			:film="film"
			:delete-film="deleteFilm"
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
