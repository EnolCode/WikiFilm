<script setup>
	import submitButton from "@/components/btns/SubmitButton.vue";
</script>

<template>
	<main>
		<h1>¡Esta Ruta No Existe!</h1>
		<h2>Vuelve a Home</h2>
		<router-link
			to="/"
			class="nav-bar__link"
		>
			<submitButton text="INICIO" />
		</router-link>
	</main>
</template>

<style lang="scss">
	main {
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
		height: 50vh;

		h1,
		h2 {
			font-size: 3em;
			color: red;
		}
	}
</style>
