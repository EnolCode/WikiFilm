import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';
import ApiService from '@/services/ApiService.js';

const apiService = new ApiService();

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
            const data = await apiService.get("/api/shows");
            this.shows.value = data
        } catch (error) {
            console.log(error)
        }
    }

    async fetchWatchList(username){
        try {
            const data  = await apiService.get(`/api/users/username/${username}`)
            this.watchList.value = data.shows;
        } catch (error) {
            console.log(error)
        }
    }

    async deleteShow(idShow){
        try {
            await apiService.delete(`/api/shows/${idShow}`)
        } catch (error) {
            console.log(error)
        }
    }
}