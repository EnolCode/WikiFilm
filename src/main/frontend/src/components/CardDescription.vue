<script setup>
	import { ref, defineProps } from "vue";

	const props = defineProps({
		titulo: Array,
	});
</script>

<template>
	<section class="card-description">
		<img
			class="card-description__image"
			:src="'http://localhost:8080/media/' + titulo.image"
			alt="film image"
			onerror="this.src='https://wallpapercave.com/wp/wp1932771.jpg'"
		/>
		<div class="card-description__text">
			<h1 class="card-description__title">{{ titulo.title }}</h1>
			<div class="card-description__text-inline">
				<p class="card-description__year">{{ titulo.releaseYear }}</p>
				<p class="card-description__year">{{ titulo.id }}</p>
				<p class="card-description__year">{{ titulo.rating }}</p>
				<p
					class="card-description__author"
					v-if="titulo.author"
				>
					{{ titulo.author.name }} {{ titulo.author.surname }}
				</p>
				<p
					class="card-description__id"
					v-if="titulo.genres && titulo.genres.length > 0"
				>
					{{ titulo.genres[0].name }}
				</p>
			</div>
			<p class="card-description__description">
				{{ titulo.description }}
			</p>
		</div>
	</section>
</template>

<style lang="scss">
	@use "@/scss/colors" as c;
	@use "@/scss/mixins" as m;

	.card-description {
		margin-top: 1em;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 80%;
		color: white;

		&__text {
			display: flex;
			flex-direction: column;
			align-items: start;
			width: 70%;

			&-inline {
				display: flex;
				justify-content: space-around;
				margin-bottom: 1em;
				color: grey;

				p {
					padding: 0 0.5em;
					border-right: 1px solid white;

					&:nth-child(1) {
						padding-left: 0;
					}

					&:last-child {
						border-right: none;
					}
				}
			}
		}

		&__image {
			width: 70%;
		}

		&__title {
			font-size: 3em;
			font-weight: 700;
			color: red;
		}
		&__id,
		&__description {
		}
	}
</style>
