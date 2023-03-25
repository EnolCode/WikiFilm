<script setup></script>

<template>
	<button
		type="submit"
		class="btn-add"
	>
		UNETE GRATIS A WIKIFILM
	</button>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;
	.btn-add {
		width: 35%;
		height: 3em;
		background-color: map-get(c.$colors, "red");
		color: map-get(c.$colors, "white");
		font-size: 1em;
		font-weight: bold;
		cursor: pointer;

		&:hover{
                @include m.pointer-opacity();
            }
	}
</style>
