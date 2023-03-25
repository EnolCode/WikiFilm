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
		padding: 1em;
		display: flex;
		justify-content: space-between;
		align-items: center;


		&:hover {
			background-color: map-get(c.$colors, "black");
		}

		.header__search {
			cursor: pointer;
		}

		.nav-bar {
			display: flex;
			justify-content: space-between;
			align-items: center;
			width: 45%;

			&__link {
				color: map-get(c.$colors, "white");
				letter-spacing:1px;
				text-decoration: none;
				font-size: 0.9em;
				font-weight: 600;
				margin: 0 1em;

				&:hover {
					color: map-get(c.$colors, "red");
				}
			}
		}

		.sign-in {
			color: map-get(c.$colors, "white");
			text-decoration: none;
			font-size: 0.9em;
			font-weight: 600;
			margin: 0 1em;

			&:hover{
                @include m.pointer-opacity();
            }
		}
	}
</style>
