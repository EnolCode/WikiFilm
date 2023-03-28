<script setup>
	import SubmitButton from "../components/btns/SubmitButton.vue";
	import ResetButton from "../components/btns/ResetButton.vue";
	import NavBar from "../components/NavBar.vue";
</script>

<template>
	<main class="login">
		<picture class="login__img-container">
			<!-- <img
				src="@/assets/images/peaky-blinders.svg"
				alt="peaky blinders"
				class="login__img"
			/> -->
		</picture>
		<div class="login__form-container">
			<NavBar />
			<div class="container-text">
				<router-link
					to="/"
					class="login__logo"
				>
					<img
						src="@/assets/images/logo-W.png"
						alt="logo"
				/></router-link>
				<h1 class="login__title">
					Inicia sesión<span class="red">.</span>
				</h1>
				<h2 class="login__subtitle">
					¿No eres miembro? <span class="red">Registrate.</span>
				</h2>
			</div>

			<form class="form">
				<input
					type="text"
					name="username"
					placeholder="Nombre de usuario"
					class="form__input"
				/>
				<input
					type="text"
					name="password"
					placeholder="Introduce tu contraseña"
					class="form__input"
				/>

				<ResetButton class="btn-reset" />
				<SubmitButton text="INICIA SESION" class="btn-submit" />
			</form>
		</div>
	</main>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.login {
		display: grid;
		grid-template-columns: 1.1fr 0.9fr;
		justify-items: center;
		align-items: center;
		background: map-get(c.$colors, "black");

		&__img-container {
			height: 100vh;
			background: url("../assets/images/peaky-blinders.svg");
			background-repeat: no-repeat;
			background-size: cover;
			background-position: 0 -100px;
			width: 120%;
		}

		.login__logo {
			width: 5em;
			align-self: center;
			position: relative;
			bottom: 20%;
		}
		&__form-container {
			@include m.flex(flex, column, auto, space-between, auto);
				height: 60%;
		align-self: start;
		margin-top: 8em;
	

			.form {
				@include m.flex(flex, column, auto, auto, auto);

				&__input {
					background: map-get(c.$colors, "grey");
					padding: 1em;
					width: 20em;
					margin: 1em 0;
					color: white;

					&::placeholder {
						color: white;
					}
				}
			}

			.container-text {
				@include m.flex(flex, column, auto, center, start);
				height: 50%;

				.login__join-text {
					@include m.font(
						500,
						0.8em,
						map-get(c.$colors, "white-light")
					);
				}

				.login__title {
					@include m.font(700, 3em, map-get(c.$colors, "white"));
				}

				.login__subtitle {
					@include m.font(
						500,
						1em,
						map-get(c.$colors, "white-light")
					);

					span {
						&:hover {
							@include m.pointer-opacity();
						}
					}
				}
			}

			.btn-reset {
				width: 20em;
				margin-top: 2em;
			}

			.btn-submit {
				width: 20em;
				margin-top: 1em;
			}
		}
	}
</style>
