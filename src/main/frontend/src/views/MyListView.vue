<script setup>
	import { ref, reactive, onBeforeMount, computed } from "vue";
	import CardFilm from "@/components/CardFilm.vue";
	import HeaderUser from "@/components/HeaderUser.vue";
	import FilmService from "@/services/FilmService.js";
	import { useAuthStore } from "@/stores/authStore";

	const auth = useAuthStore();
	const service = new FilmService();
	let watchList = ref([]);

	onBeforeMount(async () => {
		await service.fetchWatchList(auth.username);
		watchList.value = service.getWatchList();
		console.log(watchList.value);
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
		justify-content: center;
		align-items: center;
		gap: 1em;
		grid-template-columns: repeat(3, 1fr);

	}
</style>
