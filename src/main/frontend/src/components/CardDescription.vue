<script setup>
	import { ref, defineProps } from "vue";

    const props = defineProps({
        titulo: Object
    })
</script>

<template>
	<section class="card-description">
		<img
                class="card-description__image"
				:src="'http://localhost:8080/media/' + titulo.image"
				alt="film image"
				onerror="this.src='https://wallpapercave.com/wp/wp1932771.jpg'"
			/>
		<h1 class="card-description__title">{{ titulo.title }}</h1>
		<h2 class="card-description__id">{{ titulo.id }}</h2>
		<h3 class="card-description__description">{{ titulo.description }}</h3>
	</section>
</template>

<style lang="scss">
    .card-description {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width:70%;

        &__image{
            width: 90%;
        }

        &__title,
        &__id,
        &__description{
            color: white;
        }
    }

</style>
