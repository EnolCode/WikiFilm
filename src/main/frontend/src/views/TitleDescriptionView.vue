<script setup>
import { onBeforeMount, ref } from 'vue';
import axios from 'axios'
import { useRoute } from "vue-router";


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
<h1>{{ titulo.title }}</h1>
<h2>{{ titulo.id }}</h2>
<h3>{{ titulo.description }}</h3>
</template>

<style lang="scss">
    h1,h2,h3 {color:white;}
</style>