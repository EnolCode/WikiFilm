<script setup>
	import { ref } from "vue";
	import { Slide } from "vue3-burger-menu";
	import { useAuthStore } from "@/stores/authStore";
	import AuthService from "@/services/auth/AuthService.js";

	const auth = useAuthStore();
	const roles = ref(auth.roles[0]);

	const logout = async () => {
		const authService = new AuthService();
		try {
			const response = await authService.logout();
		} catch (error) {
			console.log(error);
		}
	};
</script>

<template>
	<header class="header">
		<i
			class="fa-solid fa-magnifying-glass header__search"
			style="color: #ffffff"
		></i>
		<nav class="nav-bar">
			<router-link
				to="/"
				class="nav-bar__link"
			>
				<p>Series</p>
			</router-link>

			<router-link
				to="/"
				class="nav-bar__link"
			>
				<p>Peliculas</p>
			</router-link>

			<img
				src="@/assets/images/logo-W.png"
				class="nav-bar__logo"
				alt="logo wikifilm"
			/>

			<router-link
				to="/dashboard"
				class="nav-bar__link"
			>
				Ranking
			</router-link>
			<router-link
				to="/"
				class="nav-bar__link"
			>
				<p>Recomendados</p>
			</router-link>
		</nav>
		<Slide
			width="250"
			right
			class="slide"
		>
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
		</Slide>
		<router-link
			to="/login"
			class="sign-in nav-bar__link"
			href=""
			v-if="roles === undefined"
			>INICIA SESION</router-link
		>
		<router-link
			to="/"
			class="logout nav__link nav-bar__user"
			href=""
			v-if="roles !== undefined"
		>
			<img
				src="@/assets/images/user.png"
				alt="avatar user"
			/>

			{{ auth.username }}
		</router-link>
	</header>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.header {
		@include m.flex(flex, row, auto, space-between, center);
		padding: 1em;
		background:  map-get(c.$colors, "grey-dark");
		position: fixed;
		width: 100%;

		.header__search {
			margin: 0 1em;
			cursor: pointer;

			@include m.mv(750px) {
				font-size: 1.7em;
				margin: 0em;
			}
		}

		.nav-bar {
			@include m.flex(flex, row, auto, space-around, center);
			@include m.font(400, 0.8em, map-get(c.$colors, "white"));
			width: 40%;
			position: relative;
			left: 2.8%;

			&__link {
				letter-spacing: 1px;
				margin: 0 1em;
				&:hover {
					color: map-get(c.$colors, "red");
				}
			}

			&__logo {
				position: relative;
				bottom: -1em;
				width: 6%;
				transform: scale(2.2);
			}

			&__user {
				@include m.flex(flex, row, auto, space-around, center);
				& img {
					width: 2em;
					margin-right: 0.5em;
				}
			}

			@include m.mv(750px) {
				display: none;
			}
		}

		.sign-in,
		.logout {
			@include m.font(600, 0.8em, map-get(c.$colors, "white"));
			margin: 0 1em;

			&:hover {
				@include m.pointer-opacity();
			}

			@include m.mv(750px) {
				display: none;
			}
		}
		.slide {
			display: none;

			@include m.mv(750px) {
				display: block;
				position: relative;
				right: 20%;
				top: -3em;

				.bm-burger-bars {
					background-color: white;
				}
			}
		}
	}
</style>
