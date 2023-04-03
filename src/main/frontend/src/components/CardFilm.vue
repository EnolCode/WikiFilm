<script setup>
	import { ref, defineProps } from "vue";

	const props = defineProps({
		film: {
			type: Object,
		},
	})

	const isLiked = ref(false);
	const isDisliked = ref(false);
	const like = () => {
		isLiked.value = !isLiked.value;
		isDisliked.value = false;
	};

	const dislike = () => {
        isDisliked.value = !isDisliked.value;
		isLiked.value = false;
	};


</script>
<template>
	<div class="card-film">
		<picture class="card-film__image">
			<img
				src="@/assets/images/BBWW.jpg"
				alt="Walter White"
			/>
			<div class="card-film__info">
				<p class="card-film__title"> {{ film.title }} </p>
				<span class="card-film__year">{{ film.releaseYear }} </span>
			</div>
		</picture>
		<div class="card-film__container-btns">
			<div class="separator-btns">
				<i class="fa-solid fa-check card-film__btn btn--check"></i>
				<i class="fa-solid fa-play card-film__btn btn--play"></i>
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
				<span class="card-film__rating">{{film.rating}}%</span>
			</div>
		</div>
	</div>
</template>

<style lang="scss">
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;
	.card-film {
		&__image {
			.card-film__info {
				@include m.flex(flex, row, auto, space-around, center);
				width: 10em;
				color: map-get(c.$colors, "white");
				position: relative;
				bottom: 2em;

				.card-film__title {
					font-weight: bold;
				}

				.card-film__year {
					font-size: 0.8em;
					color: white;
					background: map-get(c.$colors, "grey");
					padding: 0.2em;
					border-radius: 2px;
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
				width: 18%;
			}
			.card-film__btn {
				color: map-get(c.$colors, "white");
				cursor: pointer;
				padding: 0.2em;
			}

			.btn--check {
				color: map-get(c.$colors, "purple");
				font-size: 1.1em;
				&:hover {
					background: map-get(c.$colors, "purple");
					color: map-get(c.$colors, "white");
				}
			}

			.btn--play {
				color: map-get(c.$colors, "green");
				&:hover {
					background: map-get(c.$colors, "green");
					color: map-get(c.$colors, "white");
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

		.btn--like.active,
		.btn--dislike.active {
			color: map-get(c.$colors, "orange");
		}
	}
</style>
