<script setup>
	import SubmitButton from "../components/btns/SubmitButton.vue";
	import ResetButton from "../components/btns/ResetButton.vue";
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
		<div class="datos">
			
			<img
				src="@/assets/images/logo-W.png"
				alt="logo"
				class="register__logo"
			/>
			<p class="register__join-text">Únete gratis.</p>
			<h1 class="register__title">Crea tu cuenta<span class="red">.</span></h1>
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
				<ResetButton />
				<SubmitButton />
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

		}
		&__form-container{
			display:flex;
			flex-direction: column;
			height: 70%;
			background:blue;
			justify-content: space-between;
		}
		.form {
			&__input {
				background: map-get(c.$colors, "grey");
			}
		}

		.datos{
			display:flex;
			flex-direction: column;
			align-items: start;
			justify-content: space-evenly;
			height: 30%; 
			background: grey;

			.register__join-text{
				color: map-get(c.$colors, "white");
				font-size: .8em;
				font-weight: 500;
			}

			.register__title{
				color: map-get(c.$colors, "white");
				font-size: 3em;
				font-weight: 700;
			}

			.register__subtitle{
				color: map-get(c.$colors, "white");
				font-size: 1.2em;
				font-weight: 500;

				span{
					color: map-get(c.$colors, "red");
					cursor: pointer;
				}
			}
		}
	}
</style>
