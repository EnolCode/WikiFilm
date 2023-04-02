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

    async addFilm()
}