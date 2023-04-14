<script setup>
	import { ref, defineProps, watch } from "vue";
	import axios from "axios";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useAuthStore } from "@/stores/authStore";

	const filmStore = useFilmStore();
	const auth = useAuthStore();

	const props = defineProps({
		film: {
			type: Object,
		},
		deleteFilm: {
			type: Function,
		},
	});

	filmStore.getWatchList().then(res => {
		const watchListIds = res.map(film => film.id);
		isWatched.value = watchListIds.includes(props.film.id);
	});

	const isWatched = ref(false);
	const isLiked = ref(false);
	const isDisliked = ref(false);

	const like = () => {
		const idFilm = props.film.id;
		isLiked.value = !isLiked.value;
		isDisliked.value = false;
		axios({
			method: "POST",
			url: "http://localhost:8080/api/films/like/" + idFilm,
			withCredentials: true,
			headers: {
				"Content-Type": "application/json",
			},
		});
	};

	const dislike = () => {
		const idFilm = props.film.id;
		isDisliked.value = !isDisliked.value;
		isLiked.value = false;
		axios({
			method: "POST",
			url: "http://localhost:8080/api/films/dislike/" + idFilm,
			withCredentials: true,
			headers: {
				"Content-Type": "application/json",
			},
		});
	};

	const addFilmWatchList = () => {
		const idFilm = props.film.id;
		isWatched.value
			? (isWatched.value = false)
			: (isWatched.value = true);
		axios({
			method: "POST",
			url: "http://localhost:8080/api/users/addFilm/" + idFilm,
			withCredentials: true,
			headers: {
				"Content-Type": "application/json",
			},
		});
	};

	watch(isLiked, (newValue, oldValue) => {
		if (newValue) {
			props.film.rating++;
		} else {
			props.film.rating--;
		}
	});

	watch(isDisliked, (newValue, oldValue) => {
		if (newValue) {
			props.film.rating--;
		} else {
			props.film.rating++;
		}
	});

	const onDelete = () => {
		if (confirm("¿Seguro que quieres eliminar esta película?")) {
			props.deleteFilm(props.film.id);
		}
	};
</script>
<template>
	<div class="card-film">
		<i
			v-if="auth.roles == 'ROLE_ADMIN'"
			class="fa-solid fa-xmark delete-film"
			@click="onDelete"
		></i>
		<picture class="card-film__image">
			<img
				:src="'http://localhost:8080/media/' + film.image"
				alt="film image"
				onerror="this.src='https://wallpapercave.com/wp/wp1932771.jpg'"
			/>
			<div class="card-film__info">
				<p class="card-film__title">{{ film.title }}</p>
				<span class="card-film__year">{{ film.releaseYear }} </span>
			</div>
		</picture>
		<div class="card-film__container-btns">
			<div class="separator-btns">
				<i
					class="fa-solid fa-check card-film__btn btn--check"
					:class="{ watched: isWatched }"
					@click="addFilmWatchList"
				></i>
				<div
					v-if="!isWatched"
					class="text-hover text-hover--check"
				>
					Añade este titulo a tu lista.
				</div>
				<div
					v-if="isWatched"
					class="text-hover text-hover--check"
				>
					Elimina este titulo de tu lista.
				</div>
				<i class="fa-solid fa-play card-film__btn btn--play"></i>
				<div class="text-hover text-hover--play">
					Reproduce este titulo.
				</div>
				<i
					class="fa-solid fa-thumbs-up card-film__btn btn--like"
					:class="{ active: isLiked }"
					@click="like"
				></i>
				<i
					class="fa-solid fa-thumbs-down card-film__btn btn--dislike"
					:class="{ active: isDisliked }"
					@click="dislike"
				></i>
			</div>
			<div class="separator-rating">
				<i class="fa-solid fa-heart card-film__btn btn-rating"></i>
				<span class="card-film__rating">{{ film.rating }}%</span>
			</div>
		</div>
	</div>
</template>

<style lang="scss" scoped>
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;
	.card-film {
		position: relative;
		border: 1px solid white;
		&__image {
			width: 100%;
			img{
				width: 100%;
				height: 100%;
				// object-fit: contain;
			}
			.card-film__info {
				@include m.flex(flex, row, auto, start, center);
				color: map-get(c.$colors, "white");
				position: relative;
				bottom: 2em;
				padding-left: 0.5em;

				.card-film__title {
					font-weight: bold;
					margin-right: 0.5em;
				}

				.card-film__year {
					font-size: 0.8em;
					color: white;
					background: map-get(c.$colors, "grey");
					padding: 0.3em;
					border-radius: 3px;
				}
			}
		}

		&__container-btns {
			@include m.flex(flex, row, auto, space-between, center);
			position: relative;
			height: 0.6em;
			bottom: 0.5em;
			font-size: 1.2em;
			padding: 0 0.3em;

			.separator-btns {
				display: flex;
				justify-content: space-between;
				width: 10%;
			}
			.card-film__btn {
				color: map-get(c.$colors, "white");
				cursor: pointer;
				padding: 0.2em;
			}

			.text-hover {
				display: none;
				position: absolute;
				z-index: 5;
				border-radius: 5px;
				font-size: 0.8em;
				padding: 0.3em;
				width: 6em;
				color: map-get(c.$colors, "white");
			}

			.btn--check {
				color: map-get(c.$colors, "purple");

				&:hover {
					background: map-get(c.$colors, "purple");
					color: map-get(c.$colors, "white");
				}

				&:hover ~ .text-hover--check {
					display: block;
					background: map-get(c.$colors, "light-purple");
					top: 2em;
				}
			}

			.btn--play {
				color: map-get(c.$colors, "green");
				&:hover {
					background: map-get(c.$colors, "green");
					color: map-get(c.$colors, "white");
				}

				&:hover ~ .text-hover--play {
					display: block;
					background: map-get(c.$colors, "light-green");
					top: 2em;
				}
			}

			.btn--like,
			.btn--dislike {
				&:hover {
					color: map-get(c.$colors, "orange");
				}
			}
		}
		&__rating {
			color: map-get(c.$colors, "white");
			font-weight: bold;
		}
		.btn-rating {
			color: map-get(c.$colors, "red");
		}

		.active {
			color: map-get(c.$colors, "orange");
		}

		.watched {
			background: map-get(c.$colors, "purple");
			color: white;
		}

		.delete-film {
			color: red;
			position: absolute;
			font-size: 1.5em;
			top: 2%;
			right: 3%;
			cursor: pointer;
			&:hover {
				opacity: 0.8;
			}
		}
	}
</style>
