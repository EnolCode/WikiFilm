<script setup>
	import SubmitButton from "../components/btns/SubmitButton.vue";
	import ResetButton from "../components/btns/ResetButton.vue";
	import NavBar from "../components/NavBar.vue";
</script>

<template>
	<main class="register">
		<picture class="register__img-container">
			<img
				src="@/assets/images/deadpool.jpg"
				alt="deadpool"
				class="register__img"
			/>
		</picture>
		<div class="register__form-container">
		<NavBar />
			<div class="container-text">
				<router-link
					to="/"
					class="register__logo"
				>
					<img
						src="@/assets/images/logo-W.png"
						alt="logo"
				/></router-link>
				<p class="register__join-text">Únete gratis.</p>
				<h1 class="register__title">
					Crea tu cuenta<span class="red">.</span>
				</h1>
				<h2 class="register__subtitle">
					¿Ya eres miembro? <span class="red">Inicia sesión</span>
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
					placeholder="Introduce una contraseña"
					class="form__input"
				/>
				<input
					type="text"
					name="password"
					placeholder="Repite la contraseña"
					class="form__input"
				/>
				<ResetButton class="btn-submit" />
				<SubmitButton text="REGISTRATE" />
			</form>
		</div>
	</main>
</template>

<style lang="scss" scoped>
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	.register {
		display: grid;
		grid-template-columns: 1.1fr 0.9fr;
		justify-items: center;
		align-items: center;
		background: map-get(c.$colors, "black");

		&__img-container {
			width: 100%;
			height: 100vh;
			.register__img {
				width: 100%;
				object-fit: cover;
				height: 100vh;
			}
		}

		.register__logo {
			width: 5em;
			align-self: center;
			position: relative;
			bottom: 20%;
		}
		&__form-container {
			@include m.flex(flex, column, auto, space-between, auto);
			height: 70%;

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
				height: 30%;

				.register__join-text {
					@include m.font(
						500,
						0.8em,
						map-get(c.$colors, "white-light")
					);
				}

				.register__title {
					@include m.font(700, 3em, map-get(c.$colors, "white"));
				}

				.register__subtitle {
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

			.btn-submit {
				width: 20em;
				margin-top: 1em;
			}
		}
	}
</style>
