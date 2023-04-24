<script setup>
	import { ref, defineProps, watch, onBeforeMount } from "vue";
	import axios from "axios";
	import { useFilmStore } from "@/stores/FilmStore.js";
	import { useShowStore } from "@/stores/ShowStore.js";
	import FilmService from "@/services/FilmService.js";
	import ShowService from "@/services/ShowService.js";
	import UserService from "@/services/UserService.js";
	import { useAuthStore } from "@/stores/authStore";
	import { useRouter } from "vue-router";

	const auth = useAuthStore();
	const filmStore = useFilmStore();
	const showStore = useShowStore();
	const filmService = new FilmService();
	const showService = new ShowService();
	const userService = new UserService();
	const router = useRouter();

	const props = defineProps({
		film: {
			type: Object,
		},
		show: {
			type: Object,
		},
		deleteFilm: {
			type: Function,
		},
		deleteShow: {
			type: Function,
		},
		type: {
			type: String,
			default: "film",
		},
	});

	let prueba = ref();
	onBeforeMount(async () => {
		await userService.fetchWatchList(auth.username);
		const res = await userService.getWatchList();
		const watchListIds = res.map(item => item.title);
		const item = props.type === "film" ? props.film : props.show;
		isWatched.value = watchListIds.includes(item.title);
	});

	const titleDescription = () => {

			router.push(`title/films/${props.film.title}`);
		
	};

	const isWatched = ref(false);
	const isLiked = ref(false);
	const isDisliked = ref(false);

	const like = () => {
		const item = props.type === "film" ? props.film : props.show;
		const id = item.id;
		isLiked.value = !isLiked.value;
		isDisliked.value = false;
		props.type === "film"
			? filmService.likeFilm(id)
			: showService.likeShow(id);
	};

	const dislike = () => {
		const item = props.type === "film" ? props.film : props.show;
		const id = item.id;
		isDisliked.value = !isDisliked.value;
		isLiked.value = false;
		props.type === "film"
			? filmService.dislikeFilm(id)
			: showService.dislikeShow(id);
	};

	const addFilmWatchList = () => {
		const item = props.type === "film" ? props.film : props.show;
		const id = item.id;
		isWatched.value
			? (isWatched.value = false)
			: (isWatched.value = true);
		props.type === "film"
			? filmService.addFilmToWatchList(id)
			: showService.addShowToWatchList(id);
	};

	watch(isLiked, (newValue, oldValue) => {
		if (props.type === "film") {
			if (newValue) {
				props.film.rating++;
			} else {
				props.film.rating--;
			}
		} else {
			if (newValue) {
				props.show.rating++;
			} else {
				props.show.rating--;
			}
		}
	});

	watch(isDisliked, (newValue, oldValue) => {
		if (props.type === "film") {
			if (newValue) {
				props.film.rating--;
			} else {
				props.film.rating++;
			}
		} else {
			if (newValue) {
				props.show.rating--;
			} else {
				props.show.rating++;
			}
		}
	});

	const onDelete = () => {
		if (props.type === "film") {
			if (confirm("¿Seguro que quieres eliminar esta película?")) {
				props.deleteFilm(props.film.id);
			}
		} else {
			if (confirm("¿Seguro que quieres eliminar esta serie?")) {
				props.deleteShow(props.show.id);
			}
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
				v-if="type == 'film'"
				:src="'http://localhost:8080/media/' + film.image"
				alt="film image"
				onerror="this.src='https://wallpapercave.com/wp/wp1932771.jpg'"
			/>
			<img
				v-else
				:src="'http://localhost:8080/media/' + show.image"
				alt="film image"
				onerror="this.src='https://wallpapercave.com/wp/wp1932771.jpg'"
			/>
			<div class="card-film__info">
				<p
					class="card-film__title"
					@click="titleDescription"
				>
					{{ type == "film" ? film.title : show.title }}
				</p>
				<span class="card-film__year"
					>{{ type == "film" ? film.releaseYear : show.releaseYear }}
				</span>
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
				<span class="card-film__rating"
					>{{ type === "film" ? film.rating : show.rating }}%</span
				>
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
			img {
				width: 100%;
				height: 100%;
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
					background-color: map-get(c.$colors, "red-light");
					border-radius: 3px;
					padding: 0.1em 0.2em;
					cursor:pointer;
					&:hover {
						opacity:.8;
					}
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
