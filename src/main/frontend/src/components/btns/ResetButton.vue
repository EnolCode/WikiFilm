<script setup></script>

<template>
	<button
		type="submit"
		class="btn-reset"
	>
		LIMPIAR CAMPOS
	</button>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

	.btn-reset {
		@include m.font(bold, 1em, map-get(c.$colors, "white"));
		border: 1px solid map-get(c.$colors, "white");
		// padding:  1em 2.91em;
		width: 16em;
		height: 3em;
		cursor: pointer;

		&:hover {
			@include m.pointer-opacity();
		}
	}
</style>
