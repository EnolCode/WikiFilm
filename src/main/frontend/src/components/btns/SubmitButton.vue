<script setup>
	import { defineProps } from "vue";

	defineProps({
		text: String,
	});
</script>

<template>
	<button
		type="submit"
		class="btn-submit"
	>
		{{ text }}
	</button>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

	.btn-submit {
		@include m.font(bold, 1em, map-get(c.$colors, "white"));
		width: 14em;
		height: 3em;
		background-color: map-get(c.$colors, "red");
		cursor: pointer;

		&:hover {
			@include m.pointer-opacity();
		}
		
	}
</style>
