<script setup>
	import { ref, reactive, onBeforeMount, computed } from "vue";
	import CardFilm from "@/components/CardFilm.vue";
	import HeaderUser from "@/components/HeaderUser.vue";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useAuthStore } from "@/stores/authStore";

	const storeFilms = useFilmStore();
	const auth = useAuthStore();
	let watchList = ref([]);

	onBeforeMount(async () => {
		watchList.value = await storeFilms.getWatchList();
	});
	
</script>
<template>
	<HeaderUser />
	<main class="main">
		<CardFilm
			v-for="film in watchList"
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
