<script setup>
	import { ref, reactive, computed, onBeforeMount } from "vue";
	import axios from "axios";
	import { optionsGenres } from "@/config.js";
	import { useProgrammatic } from "@oruga-ui/oruga-next";
	import ApiService from "@/services/ApiService.js";

	const apiService = new ApiService();
	const { oruga } = useProgrammatic();
	const url = ref("");
	const imageUrl = computed(() => url.value);
	const type = ref();
	const file = ref(null);
 const typeModel = ref("");
	const onFileChange = event => {
		file.value = event.target.files[0];
	};

	const titleModel = ref("");
	const yearModel = ref("");
	const ratingModel = ref("");
	const genreModel = ref("");
	const authorModel = ref("");
	const descriptionModel = ref("");
	const authors = ref([]);

	onBeforeMount(async () => {
		try {
			const res = await axios.get(
				"http://localhost:8080/api/authors",
				{
					withCredentials: true,
				}
			);
			authors.value = res.data;
			console.log(authors.value);
		} catch (err) {
			console.log(err);
		}
	});

	const submit = async () => {
		try {
			const formData = new FormData();
			formData.append("title", titleModel.value);
			formData.append("releaseYear", yearModel.value);
			formData.append("description", descriptionModel.value);
			formData.append("genres[0].id", genreModel.value);
			formData.append("author.id", authorModel.value + 1);
			if (file.value) {
				formData.append("file", file.value);
			}

			apiService.post("show", formData);
			oruga.notification.open({
				message: "Pelicula añadida correctamente!",
				rootClass: "success-notification",
				position: "top",
				duration: 3000,
			});
			onReset();
		} catch (err) {
			oruga.notification.open({
				message: "Ha ocurrido un error al enviar el formulario.",
				rootClass: "error-notification",
				position: "top",
			});
		}
	};

	const onReset = () => {
		titleModel.value = "";
		yearModel.value = "";
		genreModel.value = "";
		authorModel.value = "";
		descriptionModel.value = "";
		file.value = null;
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

		<o-field
			label="Quieres añadir una pelicula ó una serie?"
			class="form-film-select"
		>
			<o-select
				v-model="typeModel"
				placeholder="Pelicula"
			>
				<option>Pelicula</option>
				<option>Serie</option>
			</o-select>
		</o-field>
		<o-field
			label="Selecciona un director"
			class="form-film-select"
		>
			<o-select
				v-model="authorModel"
				placeholder="Director"
			>
				<option
					v-for="(author, index) in authors"
					:value="index"
				>
					{{ author.name + " " + author.surname }}
				</option>
			</o-select>
		</o-field>

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

		<textarea
			placeholder="Descripción"
			v-model="descriptionModel"
			class="form-film__input"
		/>

		<img
			v-if="url"
			class="img"
			:src="imageUrl"
			alt=""
		/>

		<button type="submit">Submit</button>
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
		cursor: pointer;
		color: white;
		&:hover {
			opacity: 0.8;
		}
	}

	.img {
		width: 100%;
		height: 100%;
	}
</style>
