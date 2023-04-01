<script setup>
	import { ref, reactive } from "vue";
	import HeaderUser from "@/components/HeaderUser.vue";
</script>

<template>
	<HeaderUser />
	<main>
    <h1>HOLA</h1>
    </main>
</template>

<style lang="scss" >
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	main {
        height: 100vh;
		background: map-get(c.$colors, "black");
	}
</style>
