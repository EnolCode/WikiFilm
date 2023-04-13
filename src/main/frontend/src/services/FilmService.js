import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';

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
             await axios.get(`${baseUrl}/api/films`).then(res=>{
                this.films.value = res.data;
                console.log(res.data)
            })
        } catch (error) {
            console.log(error)
        }
    }

    async fetchWatchList(username) {
        try {
             await axios.get(`${baseUrl}/api/users/username/${username}`).then(res=>{
                this.watchList.value = res.data.films;
            })
        } catch (error) {
            console.log(error)
        }
    }

    async deleteFilm(idFilm) {
        try {
            await axios.delete(`${baseUrl}/api/films/${idFilm}`)
        } catch (error) {
            console.log(error)
        }
    }
}