import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';

export default class FilmService{

    films;

    constructor() {
        this.films = ref([]);
    }

    getFilms() {
        return this.films.value;
    }

    async fetchAllFilms() {
        try {
             await axios.get(`${baseUrl}/api/films`).then(res=>{
                this.films.value = res.data;
            })
        } catch (error) {
            console.log(error)
        }
    }
}