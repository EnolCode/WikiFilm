<script setup></script>

<template>
	<nav class="nav-bar">
		<router-link
			to="/"
			class="nav-bar__link"
		>
			<p>Inicio</p>
		</router-link>
		<router-link
			to="/register"
			class="nav-bar__link"
		>
			<p>Registrate</p>
		</router-link>

		<router-link
			to="/login"
			class="nav-bar__link"
		>
			<p>Inicia sesión</p>
		</router-link>
	</nav>
</template>

<style lang="scss">
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;
	.nav-bar {
		position: absolute;
        width: 20%;
        padding: 1em;
		top: 0;
		display: flex;
		justify-content: space-around;
		align-items: center;
        color: map-get(c.$colors, "white-light");
        font-weight: 300;

		@include m.mv(1300px) {
				width: 40%;
			}
			@include m.mv(900px) {
				width: 100%;
				position: sticky;
			}

@include m.mv(600px) {
			}

			@include m.mv(500px) {
				width: 110%;
				margin: 0 auto;
			}
		&__link {
			text-decoration: none;
			font-size: 0.8em;
		}
	}
</style>
