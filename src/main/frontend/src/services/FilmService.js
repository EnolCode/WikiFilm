import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';

export default class FilmService{

    films;

    constructor() {
        this.films = ref([]);
    }

    getFilms() {
        return this.films;
    }

    async fetchAllFilms() {
        try {
            const response = await axios.get(`${baseUrl}/films`).then(res=>{
                this.films.value = response.data;
            })
        } catch (error) {
            console.log(error)
        }
    }

    async addFilm()
}