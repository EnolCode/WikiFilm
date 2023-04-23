import axios from "axios";
import { ref } from "vue";
import ApiService from "@/services/ApiService.js";

const apiService = new ApiService();

export default class FilmService {
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
			this.films.value = data;
		} catch (error) {
			console.log(error);
		}
	}

	async deleteFilm(idFilm) {
		try {
			await apiService.delete(`/api/films/${idFilm}`);
		} catch (error) {
			console.log(error);
		}
	}

	async likeFilm(idFilm) {
		try {
			axios({
				method: "POST",
				url: "http://localhost:8080/api/films/like/" + idFilm,
				withCredentials: true,
				headers: {
					"Content-Type": "application/json",
				},
			});
		} catch {
			console.log(error);
		}
	}

	async dislikeFilm(idFilm) {
		try {
			axios({
				method: "POST",
				url: "http://localhost:8080/api/films/dislike/" + idFilm,
				withCredentials: true,
				headers: {
					"Content-Type": "application/json",
				},
			});
		} catch {
			console.log(error);
		}
	}

	async addFilmToWatchList(idFilm) {
		try {
			axios({
				method: "POST",
				url: "http://localhost:8080/api/users/addFilm/" + idFilm,
				withCredentials: true,
				headers: {
					"Content-Type": "application/json",
				},
			});
		} catch {
			console.log(error);
		}
	}

	async postFilm(formData){
		try{
			await apiService.post("/media/upload/film", formData);
		}catch(error){
			console.log(error);
		}
	}
}
