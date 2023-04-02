<script setup>
	import FormLogin from "@/components/forms/FormLogin.vue";
	import AuthService from "@/services/auth/AuthService.js";
	import { useRouter } from "vue-router";
	import { useAuthStore } from "@/stores/authStore";
	import { useProgrammatic } from "@oruga-ui/oruga-next";
	

	const { oruga } = useProgrammatic();
	const authStore = useAuthStore();
	const router = useRouter();

	const submitData = async formData => {
		const authService = new AuthService();
		try {
			const role = await authService.login(
				formData.username,
				formData.password
			);
			authStore.setRole(role);
			authStore.setUsername(formData.username);
			authStore.setIsAuthenticated();
			oruga.notification.open({
				message: `Hola de nuevo ${formData.username}!`,
				rootClass: "success-notification",
				position: "top",
				duration: 5000,
			});
			router.push("/dashboard");
		} catch (error) {
			if (error.response && error.response.status === 401) {
				oruga.notification.open({
					message: "Los datos son incorrectos.",
					variant: "danger",
					rootClass: "error-notification",
					duration: 3000,
					position: "top",
				});
			} else {
				oruga.notification.open({
					message: "Ha ocurrido un error al enviar el formulario.",
					rootClass: "error-notification",
					position: "top",
				});
			}
		}
	};
</script>

<template>
	<FormLogin :onSubmit="submitData" />
</template>

<style lang="scss"></style>
