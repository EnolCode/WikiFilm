<script setup>
	import FormLogin from "@/components/FormLogin.vue";
	import AuthService from "@/services/auth/AuthService.js";
	import { useRouter } from "vue-router";
	import { useAuthStore } from "@/stores/authStore";

	const authStore = useAuthStore();
	const router = useRouter();

	const submitData = async formData => {
		const authService = new AuthService();
		try {
			const role = await authService.login(
				formData.username,
				formData.password
			);
			alert("LOGADO");
			authStore.setRole(role);
			authStore.setUsername(formData.username);
			authStore.setIsAuthenticated();
			router.push("/");
		} catch (error) {
			console.log(error);
		}
	};
</script>

<template>
	<FormLogin :onSubmit="submitData" />
</template>

<style lang="scss"></style>
