import axios from "axios";
import { ref } from "vue";
import { defineStore } from "pinia";
import FilmService from "@/services/FilmService.js";
import { useAuthStore } from "@/stores/authStore";


export const useFilmStore = defineStore("film", () => {
    const authStore = useAuthStore();
    const films = ref([]);
    const watchList = ref([]);
    const service = new FilmService();

    const getAllFilms = async () => {
        await service.fetchAllFilms();
        films.value = service.getFilms();
        return films.value;
    }

    const getWatchList = async () => {
        await service.fetchWatchList(authStore.username);
        watchList.value = service.getWatchList();
        return watchList.value;
    }

    return {
        films,
        watchList,
        getAllFilms,
        getWatchList,
    }
});
