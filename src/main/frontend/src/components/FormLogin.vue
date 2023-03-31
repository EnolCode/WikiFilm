<script setup>
	import { computed, reactive, defineProps } from "vue";
	import { useVuelidate } from "@vuelidate/core";
	import SubmitButton from "../components/btns/SubmitButton.vue";
	import ResetButton from "../components/btns/ResetButton.vue";
	import NavBar from "../components/NavBar.vue";
	import { required } from "@vuelidate/validators";

	const props = defineProps({
		onSubmit: Function,
	});

	const emit = defineEmits(["submit"]);

	const form = reactive({
		username: "",
		password: "",
	});

	const rules = computed(() => {
		return {
			username: {
				required,
			},
			password: {
				required,
			},
		};
	});

	const v$ = useVuelidate(rules, form);

	const submit = async () => {
		const result = await v$.value.$validate();
		if (!result) {
			return;
		}
		const formData = {
			username: form.username,
			password: form.password,
		};
		emit("submit", formData);
		alert("enviado");
	};
</script>

<template>
	<main class="login">
		<picture class="login__img-container"> </picture>
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
					¿No eres miembro?<router-link
						to="/register"
						class="inline"
						><span class="red"> Registrate.</span>
					</router-link>
				</h2>
			</div>

			<form
				class="form"
				@submit.prevent="submit"
			>
				<input
					type="text"
					name="username"
					placeholder="Nombre de usuario"
					class="form__input"
					v-model="form.username"
				/>
				<span
					v-for="error in v$.username.$errors"
					:key="error.$uid"
					class="error-red"
				>
					{{ error.$message }}
				</span>
				<input
					type="password"
					name="password"
					placeholder="Introduce tu contraseña"
					class="form__input"
					v-model="form.password"
				/>
				<span
					v-for="error in v$.username.$errors"
					:key="error.$uid"
					class="error-red"
				>
					{{ error.$message }}
				</span> 

				<ResetButton class="btn-reset" />
				<SubmitButton
					text="INICIA SESION"
					class="btn-submit"
				/>
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
		@include m.mv(900px) {
			display: flex;
			flex-direction: column;
			align-items: center;
			height: 100vh;
		}

		&__img-container {
			height: 100vh;
			background: url("../assets/images/peaky-blinders.svg");
			background-repeat: no-repeat;
			background-size: cover;
			background-position: 0 -100px;
			width: 120%;
			@include m.mv(900px) {
				display: none;
			}
		}

		.login__logo {
			width: 5em;
			align-self: center;
			position: relative;
			bottom: 20%;
			@include m.mv(900px) {
				position: static;
			}

			@include m.mv(500px) {
				width: 3em;
			}
		}
		&__form-container {
			@include m.flex(flex, column, auto, space-between, auto);
			height: 60%;
			align-self: start;
			margin-top: 8em;

			@include m.mv(900px) {
				@include m.flex(flex, column, auto, center, center);
				height: 80%;
				margin: 0;
				width: 100%;
			}

			@include m.mv(600px) {
				height: 90%;
			}

			.form {
				@include m.flex(flex, column, auto, auto, auto);
				@include m.mv(900px) {
					@include m.flex(flex, column, auto, auto, center);
					width: 100%;
				}
				&__input {
					background: map-get(c.$colors, "grey");
					padding: 1em;
					width: 20em;
					margin: 1em 0;
					color: white;
					@include m.mv(500px) {
						width: 90%;
						margin: 0.5em auto;
					}
					&::placeholder {
						color: white;
					}
				}
			}

			.container-text {
				@include m.flex(flex, column, auto, center, start);
				height: 50%;
				@include m.mv(500px) {
					@include m.flex(flex, column, auto, center, center);
				}

				.login__join-text {
					@include m.font(
						500,
						0.8em,
						map-get(c.$colors, "white-light")
					);
				}

				.login__title {
					@include m.font(700, 3em, map-get(c.$colors, "white"));
					@include m.mv(500px) {
						font-size: 3em;
					}
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
					@include m.mv(500px) {
						font-size: 0.8em;
						text-align: center;
						width: 100%;
					}
				}
			}

			.btn-reset {
				width: 20em;
				margin-top: 2em;
				@include m.mv(500px) {
					margin-top: 1em;
					width: 90%;
				}
			}

			.btn-submit {
				width: 20em;
				margin-top: 1em;
				@include m.mv(500px) {
					margin-top: 1em;
					width: 90%;
				}
			}
		}
	}
</style>
