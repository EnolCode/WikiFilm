<script setup>
	import { ref, reactive, computed } from "vue";
	import axios from "axios";
	import { optionsGenres } from "@/config.js";

	const url = ref("");
	const imageUrl = computed(() => url.value);

	const file = ref(null);

	const onFileChange = event => {
		file.value = event.target.files[0];
	};

	const titleModel = ref("");
	const yearModel = ref("");
	const ratingModel = ref("");
	const genreModel = ref("");
	const descriptionModel = ref("");

	const submit = async () => {
		try {
			const formData = new FormData();
			formData.append("title", titleModel.value);
			formData.append("releaseYear", yearModel.value);
			formData.append("description", descriptionModel.value);
			formData.append("genres[0].id", genreModel.value);
			if (file.value) {
				formData.append("file", file.value);
			}

			await axios({
				method: "POST",
				url: "http://localhost:8080/media/upload/film",
				data: formData,
				withCredentials: true,
				headers: {
					"Content-Type": "multipart/form-data",
				},
			});
			alert("enviado");
		} catch (err) {
			console.log(err);
		}
	};
</script>
<template>
	<form
		method="post"
		class="form-film"
		@submit.prevent="submit"
	>
		<h1 class="form-film__title">Añade un nuevo titulo</h1>
		<input
			type="text"
			name="title"
			placeholder="Título"
			class="form-film__input"
			v-model="titleModel"
		/>
		<input
			type="number"
			name="year"
			placeholder="Año de estreno"
			class="form-film__input"
			v-model="yearModel"
		/>
		<input
			type="text"
			name="Autor"
			placeholder="Director"
			class="form-film__input"
		/>
		<input
			type="text"
			name="description"
			placeholder="Descripción"
			class="form-film__input"
			v-model="descriptionModel"
		/>
		<o-field
			label="Selecciona un género"
			class="form-film-select"
		>
			<o-select
				v-model="genreModel"
				placeholder="Género"
			>
				<option
					v-for="option in optionsGenres"
					:value="option.value"
				>
					{{ option.label }}
				</option>
			</o-select>
		</o-field>

		<input
			type="file"
			name="file"
			class="file-img form-film__input"
			@change="onFileChange"
		/>

		<img
			v-if="url"
			class="img"
			:src="imageUrl"
			alt=""
		/>

		<button type="submit">submit</button>
	</form>
</template>
<style lang="scss" scoped>
	.form-film {
		width: 60%;
		margin: 0 auto;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		gap: 1em;
		padding: 1em;

		&__title {
			font-size: 2em;
			color: red;
		}
		&__input {
			background: white;
			padding: 1em;
			width: 50%;
			color: black;
		}

		&-select {
			display: flex;
			justify-content: space-between;
			padding: 1em;
			width: 50%;
			background: white;
		}
	}

	button {
		background: red;
		padding: 1em;
		width: 50%;
	}

	.img {
		width: 100%;
		height: 100%;
	}
</style>
