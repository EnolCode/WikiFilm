<script setup>
	import FormRegister from "@/components/FormRegister.vue";
	import AuthService from "@/services/auth/AuthService.js";
	import { useRouter } from "vue-router";
	import { useProgrammatic } from '@oruga-ui/oruga-next'
	
	const { oruga } = useProgrammatic()

    const router = useRouter();

	const submitData = async formData => {
  const service = new AuthService();
  try {
    const response = await service.register(
      formData.username,
      formData.password
    );
    oruga.notification.open({
      message: 'Formulario enviado correctamente!',
      rootClass: 'success-notification',
      position: 'top'
    });
    router.push("/login");
  } catch (error) {
    if (error.response && error.response.status === 404) {
      oruga.notification.open({
        message: 'Lo siento, ya existe un usuario con ese nombre.',
		variant: 'danger',
        rootClass: 'error-notification',
		duration: 2000,
        position: 'top'
      });
    } else {
      oruga.notification.open({
        message: 'Ha ocurrido un error al enviar el formulario.',
        rootClass: 'error-notification',
        position: 'top',
      });
    }
  }
};
</script>

<template>
	<FormRegister :onSubmit="submitData" />
</template>

<style lang="scss">

.error-notification{
	background:red;
	padding: .5em;
	border-radius: 2px;
}

.success-notification{
	background:green;
	padding: .5em;
	border-radius: 2px;
}
</style>
