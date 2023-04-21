<script setup>
	import { ref, onBeforeMount, computed } from "vue";
	import HeaderUser from "@/components/layout/HeaderUser.vue";
	import { useShowStore } from "@/stores/ShowStore.js";
	import CardFilm from "@/components/CardFilm.vue";
	import FootPage from "@/components/layout/FootPage.vue";
	import CopyRight from "@/components/CopyRight.vue";

	const showStore = useFilmStore();
	let shows = ref([]);
	let searchShows = ref("");

	onBeforeMount(async () => {
		shows.value = await showStore.getAllShowsForRating();
	});

	const deleteShow = id => {
		shows.value = shows.value.filter(show => show.id !== id);
		service.deleteShow(id);
	};

	const filteredShowForTitle = computed(() => {
		if (!searchShows.value) return shows.value;
		return shows.value.filter(show =>
			show.title
				.toLowerCase()
				.includes(searchShows.value.toLowerCase())
		);
	});
</script>
<template>
	<HeaderUser
		v-model="searchShows"
		@update:modelValue="searchShows = $event"
	/>
	<main>
		<CardFilm
			class="card"
			v-for="show in shows"
			:show="show"
			:delete-show="deleteShow"
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
