<script setup>
	import { ref } from 'vue'
	import { Slide } from "vue3-burger-menu";
	import { useAuthStore } from "@/stores/authStore";
	
	const auth = useAuthStore();
	const roles = ref(auth.roles[0]);

	const logout = async () =>{
		const authService = new AuthService();
		try {
			const response = await authService.logout();
		} catch (error) {
			console.log(error)
		}
	}
	console.log(auth.roles[0]);
</script>

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
			class="sign-in nav__link"
			href=""
			v-if="roles === undefined"
			>INICIA SESION</router-link
		>
		<router-link
			@click="logout"
			to="/"
			class="logout nav__link"
			href=""
			v-if="roles !== undefined"
			>DESCONECTAR</router-link
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
			@include m.mv(750px) {
				background: none;
			}
		}

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
			width: 55%;

			&__link {
				@include m.font(400, 0.8em, map-get(c.$colors, "white"));
				letter-spacing: 1px;
				margin: 0 1em;

				&:hover {
					color: map-get(c.$colors, "red");
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
