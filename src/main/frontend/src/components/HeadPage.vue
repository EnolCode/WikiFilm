<script setup></script>

<template>
	<header class="header">
		<i
			class="fa-solid fa-magnifying-glass header__search"
			style="color: #ffffff"
		></i>
		<nav class="nav-bar">
			<a
				href="#"
				class="nav-bar__link"
				>Series</a
			>
			<a
				href="#"
				class="nav-bar__link"
				>Peliculas</a
			>
			<a
				href="#"
				class="nav-bar__link"
				>Ranking Valoraciones</a
			>
			<a
				href="#"
				class="nav-bar__link"
				>Recomendacion semanal</a
			>
		</nav>

		<a
			href="#"
			class="sign-in"
			>REGISTRATE</a
		>
	</header>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.header {
		@include m.flex(flex, row, auto, space-between, center);
		padding: 1em;

		&:hover {
			background-color: map-get(c.$colors, "black");
		}

		.header__search {
			margin: 0 1em;
			cursor: pointer;
		}

		.nav-bar {
			@include m.flex(flex, row, auto, space-evenly, center);
			width: 65%;

			&__link {
				@include m.font(400, 0.9em, map-get(c.$colors, "white"));
				letter-spacing: 1px;
				margin: 0 1em;

				&:hover {
					color: map-get(c.$colors, "red");
				}
			}
		}

		.sign-in {
			@include m.font(600, 0.9em, map-get(c.$colors, "white"));
			margin: 0 1em;

			&:hover {
				@include m.pointer-opacity();
			}
		}
	}
</style>
