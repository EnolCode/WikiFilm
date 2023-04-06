import axios from "axios";
import { ref } from "vue";
import { baseUrl } from "@/config.js";

export default class AvatarService {
	#avatar;

	constructor() {
		this.avatar = ref([]);
	}

	async getAvatar(username) {
		try {
			const response = await axios
				.get(baseUrl + "/api/users/username/" + username)
				.then(res => (this.avatar = res.data.avatar));
			return this.avatar;
		} catch (error) {
			console.log(error);
		}
	}

	async uploadAvatar(formData) {
		try {
			axios({
				method: "POST",
				url: "http://localhost:8080/media/upload",
				data: formData,
				withCredentials: true,
			}).then(response => {
				console.log(response);
			});
		} catch (error) {
			console.log(error);
		}
	}
}
