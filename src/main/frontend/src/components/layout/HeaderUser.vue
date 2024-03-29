<script setup>
	import { ref, onBeforeMount, defineProps } from "vue";
	import { Slide } from "vue3-burger-menu";
	import { useAuthStore } from "@/stores/authStore";
	import AuthService from "@/services/auth/AuthService.js";
	import AvatarService from "@/services/avatar/AvatarService.js";
	import FilterBar from "@/components/FilterBar.vue";
	import axios from "axios";

	onBeforeMount(() => {
		avatarService
			.getAvatar(auth.username)
			.then(res => (url.value = res));
	});

	const props = defineProps({
		modelValue: String,
	});

	const showLogout = ref(false);
	let url = ref("");
	const auth = useAuthStore();
	const roles = ref(auth.roles[0]);
	const avatarService = new AvatarService();
	const authService = new AuthService();

	function toggleLogout() {
		showLogout.value = !showLogout.value;
	}

	const onFileChange = event => {
		const file = event.target.files[0];
		if (file) {
			const formData = new FormData();
			formData.append("file", file);
			avatarService.uploadAvatar(formData);
		}
	};

	const logout = async () => {
		await authService.logout();
	};
</script>

<template>
	<header class="header">
		<FilterBar
			:modelValue="modelValue"
			@update:modelValue="$emit('update:modelValue', $event)"
		/>
		<nav class="nav-bar">
			<router-link
				to="/shows"
				class="nav-bar__link"
			>
				<p>Series</p>
			</router-link>

			<router-link
				to="/films"
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
				to="/myList"
				class="nav-bar__link"
			>
				<p>Mi lista</p>
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
				>Ranking</a
			>
			<a
				href="#"
				class="nav-bar__link"
				>Estrenos</a
			>
		</Slide>
		<div
			@click="toggleLogout"
			class="nav__link nav-bar__user"
		>
			<router-link
				to="/login"
				class="nav__link"
				href=""
				v-if="roles === undefined"
				>INICIA SESION</router-link
			>

			<img
				v-else
				:src="'http://localhost:8080/media/' + url"
				alt="avatar user"
				class="nav-bar__avatar"
				onerror="this.src='src/assets/images/user.png'"
			/>

			{{ auth.username }}

			<div
				class="nav-bar__user-actions"
				v-if="showLogout"
			>
				<router-link
					to="/"
					href=""
					class="user-actions__btn"
					@click="logout"
					>Cerrar sesion
				</router-link>

				<input
					type="file"
					accept="image/*"
					ref="fileInput"
					@change="onFileChange"
					class="hidden"
					hidden
				/>
				<p
					class="user-actions__btn"
					@click="$refs.fileInput.click()"
				>
					Cambiar avatar
				</p>

				<router-link
					to="/addFilm"
					v-if="auth.roles == 'ROLE_ADMIN'"
					class="user-actions__btn"
					>Añadir título
				</router-link>

			</div>
		</div>
	</header>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

	.header {
		@include m.flex(flex, row, auto, space-between, center);
		padding: 0.5em;
 	background: map-get(c.$colors, "black");
		position: sticky;
		top: 0;
		width: 100%;
		z-index: 2;

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
			left: -1.9%;

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
				transform: scale(1.5);
			}

			&__user {
				@include m.flex(flex, row, auto, space-around, center);
				@include m.font(600, 0.8em, map-get(c.$colors, "white"));
				position: relative;
				margin: 0 1em;
				&:hover {
					@include m.pointer-opacity();
				}

				@include m.mv(750px) {
					display: none;
				}
				& img {
					width: 2em;
					height: 2em;
					margin-right: 0.5em;
					border-radius: 50%;
				}
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
					background-color: map-get(c.$colors, "white");
				}
			}
		}

		.nav-bar__user-actions {
			@include m.flex(flex, column, auto, center, center);
			position: absolute;
			top: 3em;
			border-radius: 5px;
			padding: 1em 0em;
			gap: 1em;
			width: 8.5em;
			left: -3em;
			background-color: map-get(c.$colors, "light-grey");
			.user-actions__btn {
				&:hover {
					color: map-get(c.$colors, "black");
				}
			}
		}
		.nav-bar__user-actions::before {
			content: "";
			position: absolute;
			top: -10px;
			left: 50%;
			transform: translateX(-50%);
			border-left: 10px solid transparent;
			border-right: 10px solid transparent;
			border-bottom: 10px solid map-get(c.$colors, "light-grey");
		}
	}
</style>
