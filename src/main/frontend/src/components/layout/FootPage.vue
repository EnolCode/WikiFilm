<script setup>
	import SocialMedia from "@/components/SocialMedia.vue";
	import { useProgrammatic } from "@oruga-ui/oruga-next";

	const { oruga } = useProgrammatic();

	const subscribeNewsletter = () => {
		oruga.notification.open({
			message: "Gracias por suscribirte a nuestra newsletter!",
			rootClass: "success-notification",
			position: "top",
			duration: 4000,
		});
	};
</script>

<template>
	<footer class="footer">
		<div class="footer__newsletter">
			<h2 class="footer__newsletter__title">
				Unete a nuestra Newsletter para recibir las ultimas noticias
				del mundo audiovisual.
			</h2>
			<form
				class="footer__newsletter__form"
				@submit.prevent="subscribeNewsletter"
			>
				<input
					type="email"
					class="footer__newsletter__input"
					placeholder="Introduce tu email"
				/>
				<button class="footer__newsletter__button">
					Subscribete
				</button>
			</form>
		</div>

		<nav class="footer__nav">
			<ul class="footer__nav__list">
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>HOME</a
					>
				</li>
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>ABOUT</a
					>
				</li>
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>CONTACT</a
					>
				</li>
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>BLOG</a
					>
				</li>
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>TERMS</a
					>
				</li>
				<li class="footer__nav__list__item">
					<a
						href="#"
						class="footer__nav__list__item__link"
						>PRIVACY</a
					>
				</li>
			</ul>

			<SocialMedia />
		</nav>
	</footer>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

	.footer {
		@include m.flex(flex, column, auto, center, center);
		width: 100%;
		background: map-get(c.$colors, "red");
		margin-top: 3em;

		&__newsletter {
			@include m.flex(flex, column, auto, center, auto);
			padding: 2em;

			&__title {
				@include m.font(auto, 1.3em, map-get(c.$colors, "white"));
			}

			&__form {
				@include m.flex(flex, auto, auto, auto, auto);
				margin-top: 1em;
			}
			&__input {
				width: 60%;
				border-radius: 3px;
				padding: 0.3em;
				background: map-get(c.$colors, "white");
			}

			&__button {
				@include m.font(bold, 1em, map-get(c.$colors, "red"));
				width: 10em;
				height: 2em;
				border-radius: 3px;
				background: map-get(c.$colors, "white");
				cursor: pointer;
				margin-left: 1em;
				&:hover {
					background: map-get(c.$colors, "red");
					color: map-get(c.$colors, "white");
					border: 1px solid map-get(c.$colors, "white");
				}
			}
		}

		&__nav {
			@include m.flex(flex, auto, auto, auto, center);
			font-size: 0.8em;
			justify-content: space-between;
			background: map-get(c.$colors, "black");
			width: 100%;
			padding: 1em;

			&__list {
				display: flex;
				gap: 1em;

				&__item {
					&__link {
						color: map-get(c.$colors, "white");

						&:hover {
							color: map-get(c.$colors, "red");
						}
					}
				}
			}
		}
	}
</style>
