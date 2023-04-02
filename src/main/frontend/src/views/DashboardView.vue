<script setup>
	import { ref, reactive } from "vue";
	import HeaderUser from "@/components/HeaderUser.vue";
	import CardFilm from "@/components/CardFilm.vue";
</script>

<template>
	<HeaderUser />
	<main>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>
    <CardFilm class="card"/>

    </main>
</template>

<style lang="scss" >
	@use "../scss/colors" as c;
	@use "../scss/mixins" as m;

	 main {
    display: grid;
 	background: map-get(c.$colors, "black");
	width: 80%;
	gap: 1em;
	margin: 0 auto;

    grid-template-columns: repeat(6, 1fr);
    grid-auto-rows: minmax(5em, auto);
  }
  .card {
	  border: 1px solid white;
    grid-column: span 2;
  }
  .card:first-child,
  .card:nth-child(2) {
    grid-column: span 3;
  }
</style>
