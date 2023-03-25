<script setup>
	import Header from "../components/HeadPage.vue";
	import AddButton from "../components/AddButton.vue";
	import SocialMedia from "../components/SocialMedia.vue";
</script>

<template>
	<main class="main">
		<Header />

		<section class="wrapper">
			<div class="container-logo">
				<img
					src="../assets/images/logo-W.png"
					alt="logo-W"
					class="logo"
				/>
				<h1 class="title">WikiFilm</h1>
			</div>

			<div class="container-info">
				<p class="tagline">Puntua. Descubre. Busca</p>

				<p class="description">
					Puntua tus peliculas y series favoritas. Descubre las
					mejores recomendaciones según tus gustos. Unete a una gran
					comunidad de gente con ganas de compartir su opinión.
				</p>

				<AddButton />
			</div>
		</section>
		<footer class="footer">
			<SocialMedia />
		</footer>
	</main>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.main {
		@include m.flex(flex, column, auto, auto, auto);
		background-image: url("../assets/images/TLOU-ok.jpg");
		background-position: center;
		background-size: cover;
		height: 100vh;

		.wrapper {
			@include m.flex(flex, column, auto, space-around, center);
			height: 70%;

			.container-logo {
				@include m.flex(flex, column, auto, auto, center);
				margin-top: 5em;

				.logo {
					display: block;
					height: 3em;
					margin: 0 auto;
					width: 5em;
				}

				.title {
					border-bottom: 1px solid;
					color: map-get(c.$colors, "white");
					font-size: 3em;
					font-weight: bold;
				}
			}
			.container-info {
				@include m.flex(flex, column, auto, space-around, center);
				color: map-get(c.$colors, "white");
				height: 45%;
				width: 40%;

				.tagline {
					font-size: 2em;
					font-weight: bold;
				}

				.description {
					bottom: 10%;
					font-size: 1.2em;
					position: relative;
					text-align: center;
				}
			}
		}
		.footer {
			bottom: 0;
			position: fixed;
			width: 100%;
		}
	}
</style>
