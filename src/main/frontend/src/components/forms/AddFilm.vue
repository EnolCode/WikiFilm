<script setup>
	import axios from "axios";

	import { ref, reactive } from "vue";

	const film = {
		title: "El pianista",
		releaseYear: 2023,
		rating: 8,
		description: "Gran pelicula",
		genres: [
			{
				id: 1,
				name: "comedy",
			},
		],
	};

	const submit = async () => {
		axios({
			method: "POST",
			url: "http://localhost:8080/api/films/add",
			data: film,
			headers: {
				"Content-Type": "application/json",
			},
		})
			.then(res => alert("enviado"))
			.catch(err => console.log(err));
	};
</script>
<template>
	<form
		method="post"
		enctype="multipart/form-data"
		@submit.prevent="submit"
	>
		<input
			type="text"
			name="title"
			placeholder="title"
			class="input"
			v-model="film.title"
		/>
		<input
			type="text"
			name="year"
			placeholder="year"
			class="input"
			v-model="film.releaseYear"
		/>
		<input
			type="text"
			name="author"
			placeholder="author"
			class="input"
			v-model="film.author"
		/>
		<input
			type="text"
			name="description"
			placeholder="description"
			class="input"
			v-model="film.description"
		/>
		<input
			type="number"
			name="rating"
			placeholder="rating"
			class="input"
			v-model="film.rating"
		/>
		<!-- <label
			for="image"
			type="file"
			name="file"
			id="image"
			>Image</label
		>
		<input
			type="file"
			name="file"
			class="file-img input"
			@change="onFileChange"
		/> -->

		<button type="submit">submit</button>
	</form>
</template>
<style lang="scss" scoped>
	.input {
		background: white;
		color: black;
	}

	button {
		background: red;
	}
</style>
