<script setup>
	import { computed, reactive, defineProps } from "vue";
	import { useVuelidate } from "@vuelidate/core";
	import { useRouter } from "vue-router";
	import SubmitButton from "../components/btns/SubmitButton.vue";
	import ResetButton from "../components/btns/ResetButton.vue";
	import NavBar from "../components/NavBar.vue";
	import {
		required,
		minLength,
		minValue,
		maxValue,
		maxLength,
		sameAs,
	} from "@vuelidate/validators";
	import { helpers } from "@vuelidate/validators";

	const props = defineProps({
		onSubmit: Function,
	});

	const emit = defineEmits(["submit"]);

	const form = reactive({
		username: "",
		password: "",
		repeatPassword: "",
	});

	const containsNumber = helpers.withMessage(
		"La contraseña debe contener al menos un número",
		value => /\d/.test(value)
	);

	const rules = computed(() => {
		return {
			username: {
				required,
				minLength: minLength(3),
				maxLength: maxLength(20),
			},
			password: {
				required,
				minLength: minLength(5),
				maxLength: maxLength(15),
				containsNumber,
			},
			repeatPassword: {
				required,
				sameAsPassword: sameAs(form.password),
			},
		};
	});
	const v$ = useVuelidate(rules, form);

	const submit = async () => {
		const result = await v$.value.$validate();
		console.log(form.username, form.password, form.repeatPassword);
		if (!result) {
			return;
		}
		const formData = {
			username: form.username,
			password: form.password,
		};
		emit("submit", formData);
		// props.onSubmit(formData);
		alert("enviado");
	};
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
					¿Ya eres miembro?
					<router-link
						to="/login"
						class="inline"
						><span class="red"> Inicia sesión</span>
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
					placeholder="Introduce una contraseña"
					class="form__input"
					v-model="form.password"
				/>
				<span
					v-for="error in v$.password.$errors"
					:key="error.$uid"
					class="error-red"
				>
					{{ error.$message }}
				</span>
				<input
					type="password"
					name="repeatPassword"
					placeholder="Repite la contraseña"
					class="form__input"
					v-model="form.repeatPassword"
				/>
				<span
					v-for="error in v$.repeatPassword.$errors"
					:key="error.$uid"
					class="error-red"
				>
					{{ error.$message }}
				</span>
				<ResetButton class="btn-submit btn" />
				<SubmitButton
					text="REGISTRATE"
					class="btn"
				/>
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
		@include m.mv(900px) {
			display: flex;
			flex-direction: column;
			height: 100vh;
		}

		&__img-container {
			width: 100%;
			height: 100vh;
			@include m.mv(900px) {
				display: none;
			}
			.register__img {
				width: 100%;
				object-fit: cover;
				height: 100vh;
			}
		}

		.register__logo {
			width: 6em;
			align-self: center;
			position: relative;
			bottom: -35%;
			margin-bottom: 3em;
			@include m.mv(900px) {
				position: static;
			}

			@include m.mv(500px) {
				width: 5em;
				margin-bottom: 1em;
			}
		}
		&__form-container {
			@include m.flex(flex, column, auto, space-between, auto);
			min-height: 65%;
			@include m.mv(900px) {
				@include m.flex(flex, column, auto, space-between, center);
				height: 80%;
				margin: 0;
				width: 100%;
			}

			@include m.mv(900px) {
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

					&::placeholder {
						color: white;
					}

					@include m.mv(500px) {
						width: 90%;
						margin: 0.5em auto;
					}
				}
			}

			.container-text {
				@include m.flex(flex, column, auto, center, start);
				height: 10%;
				@include m.mv(500px) {
					@include m.flex(flex, column, auto, center, center);
				}
				.register__join-text {
					@include m.font(
						500,
						0.8em,
						map-get(c.$colors, "white-light")
					);
				}

				.register__title {
					@include m.font(700, 3em, map-get(c.$colors, "white"));
					@include m.mv(500px) {
						font-size: 2em;
					}
				}

				.register__subtitle {
					width: 100%;
					@include m.font(
						500,
						1em,
						map-get(c.$colors, "white-light")
					);

					span {
						display: inline;
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

			.btn-submit {
				margin-top: 1em;
				width: 20em;
				@include m.mv(500px) {
					width: 90%;
					margin-top: 1em;
				}
			}
		}
	}

	.error-red {
		// position: absolute;
		bottom: -1em;
		color: red;
	}
</style>
