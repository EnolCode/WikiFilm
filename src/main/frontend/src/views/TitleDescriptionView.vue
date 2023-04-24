<script setup>
import { onBeforeMount, ref } from 'vue';
import axios from 'axios'
import { useRoute } from "vue-router";
import CardDescription from "@/components/CardDescription.vue"

const route = useRoute();
const titulo = ref("");

onBeforeMount(async () => {
    const type = route.params.type;
    const title = route.params.title;
    try {
        const response = await axios.get(`http://localhost:8080/api/films/title/${title}`);
        titulo.value = response.data;
    } catch (error) {
        if (error.response && error.response.status === 404) {
            const response = await axios.get(`http://localhost:8080/api/shows/title/${title}`);
            titulo.value = response.data;
        }
    }
});
</script>

<template>
<main class="main">
<CardDescription :titulo="titulo" />
</main>
</template>

<style lang="scss">
.main {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
}
</style>