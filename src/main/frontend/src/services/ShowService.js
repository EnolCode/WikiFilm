import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';

export default class ShowService{
    shows;
    watchList;

    constructor(){
        this.shows = ref([]);
        this.watchList = ref([]);
    }

    getShows(){
        return this.shows.value;
    }

    getWatchList(){
        return this.watchList.value;
    }

    async fetchAllShows(){
        try {
            await axios.get(`${baseUrl}/api/shows`)
        } catch (error) {
            
        }
    }
}