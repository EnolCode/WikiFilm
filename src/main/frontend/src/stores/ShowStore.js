import axios from "axios";
import { ref } from "vue";
import { defineStore } from "pinia";
import ShowService from "@/services/ShowService.js";
import { useAuthStore } from "@/stores/authStore";

export const useShowStore = defineStore("show",()=>{
    const authStore = useAuthStore();
    const shows = ref([]);
    const watchList = ref([]);
    const service = new ShowService();

    const getAllShowsForRating = async () => {
        await service.fetchAllShows();
        shows.value = service.getShows();
        const sortedShows = shows.value.sort((a, b) => b.rating - a.rating);
        return shows.value;
    }

    const getWatchList = async () => {
        await service.fetchWatchList(authStore.username);
        watchList.value = service.getWatchList();
        return watchList.value;
    }

    return {
        shows,
        watchList,
        getAllShowsForRating,
        getWatchList,
    }
})