<script setup>
	import { ref, reactive,computed } from "vue";
	import axios from "axios";

	const url = ref("");
	const imageUrl = computed(() => url.value);

	const onFileChange = event => {
		const file = event.target.files[0];
		if (file) {
			const formData = new FormData();
			formData.append("file", file);
			axios({
					method: "POST",
					url: "http://localhost:8080/media/upload",
					data: formData,
				})
				.then(response => {
					url.value = response.data.url;
					console.log(response.data.url)})
				.catch(e => {console.log(e)})
		}
	};
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
		try {
			const [res1, res2] = await Promise.all([
				axios({
					method: "POST",
					url: "http://localhost:8080/api/films/add",
					data: film,
					headers: {
						"Content-Type": "application/json",
					},
				}),
			]);
			alert("enviado");
		} catch (err) {
			console.log(err);
		}
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
		<label
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
		/>

		<img v-if="url" class="img" :src="imageUrl" alt="">

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

	.img{
		width: 100%;
		height: 100%;
	}
</style>
