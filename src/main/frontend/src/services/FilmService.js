import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';
import ApiService from '@/services/ApiService.js';

const apiService = new ApiService();

export default class FilmService{

    films;
    watchList;

    constructor() {
        this.films = ref([]);
        this.watchList = ref([]);
    }

    getFilms() {
        return this.films.value;
    }

    getWatchList() {
        return this.watchList.value;
    }

    async fetchAllFilms() {
        try {
             const data = await apiService.get("/api/films");
             this.films.value = data
             console.log(data)
        } catch (error) {
            console.log(error)
        }
    }

    async fetchWatchList(username) {
        try {
             const data  = await apiService.get(`/api/users/username/${username}`)
             this.watchList.value = data.films;
        } catch (error) {
            console.log(error)
        }
    }

    async deleteFilm(idFilm) {
        try {
            await apiService.delete(`/api/films/${idFilm}`)
        } catch (error) {
            console.log(error)
        }
    }
}