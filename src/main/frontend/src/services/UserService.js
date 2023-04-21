import axios from 'axios'
import { ref } from "vue";
import { baseUrl } from "@/config.js";
import ApiService from "@/services/ApiService.js";
const apiService = new ApiService();


export default class UserService {
    watchList;

    constructor() {
        this.watchList = ref([]);
    }
    
    getWatchList() {
		return this.watchList.value;
	}

    async fetchWatchList(username) {
		try {
			const data = await apiService.get(
				`/api/users/username/${username}`
			);
			this.watchList.value = data.films;
		} catch (error) {
			console.log(error);
		}
	}
}