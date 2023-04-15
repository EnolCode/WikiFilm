<script setup>
	import Header from "../components/layout/HeadPage.vue";
	import SubmitButton from "../components/btns/SubmitButton.vue";
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
				<router-link
					to="/register"
					class="sign-in nav__link"
				>
					<SubmitButton text="UNETE A WIKIFILM GRATIS" />
				</router-link>
			</div>
		</section>
		<footer class="footer">
			<SocialMedia />
		</footer>
	</main>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

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
				@include m.mv(750px) {
					margin-top: 1em;
				}
				.logo {
					display: block;
					height: 3em;
					margin: 0 auto;
					width: 5em;
				}

				.title {
					@include m.font(bold, 3em, map-get(c.$colors, "white"));
					border-bottom: 1px solid;
				}
			}
			.container-info {
				@include m.flex(flex, column, auto, space-around, center);
				color: map-get(c.$colors, "white");
				height: 45%;
				width: 40%;
				text-align: center;

				@include m.mv(1440px) {
					width: 50%;
				}

				@include m.mv(1140px) {
					width: 70%;
				}

				@include m.mv(750px) {
					width: 100%;
					height: 55%;
				}
			}
			.tagline {
				@include m.font(bold, 2em, map-get(c.$colors, "red"));
				@include m.mv(750px) {
					font-size: 1.5em;
				}
			}

			.description {
				bottom: 10%;
				font-size: 1.2em;
				position: relative;
				text-align: center;

				@include m.mv(1140px) {
					bottom: 5%;
				}
				@include m.mv(750px) {
					font-size: 0.8em;
					width: 90%;
				}
			}
		}
		.footer {
			bottom: 0;
			right: 0;
			position: fixed;
			width: 100%;
			margin-bottom: 1em;
			margin-right: 1.2em;

		}
	}
</style>
