<script setup>
	import { ref, reactive } from "vue";
	import HeaderUser from "@/components/HeaderUser.vue";
	import CardFilm from "@/components/CardFilm.vue";
</script>

<template>
	<HeaderUser />
	<main>
    <CardFilm />
    </main>
</template>

<style lang="scss" >
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	main {
        height: 100vh;
		background: map-get(c.$colors, "black");
			@include m.flex(flex, row, auto, center, center);
	}
</style>
