import { ref, watch } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", () => {
	const initialState = JSON.parse(
		localStorage.getItem("auth") || "{}"
	);

	let isAuthenticate = ref(initialState.isAuthenticate || false);
	const username = ref(initialState.username || "");
	const roles = ref(initialState.roles || []);

	watch(
		() => ({
			isAuthenticate: isAuthenticate.value,
			username: username.value,
			roles: roles.value,
		}),
		state => {
			localStorage.setItem("auth", JSON.stringify(state));
		}
	);

	const setRole = role => {
		if (!roles.value.includes(role)) {
			roles.value.push(role);
		}
	};

	const setUsername = newUsername => {
		username.value = newUsername;
	};

	const setIsAuthenticated = () => {
		isAuthenticate.value = true;
	};

	return {
		isAuthenticate,
		username,
		roles,
		setRole,
		setUsername,
		setIsAuthenticated,
	};
});
