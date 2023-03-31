<script setup>
import { onBeforeMount } from "vue";
import FormRegister from "@/components/FormRegister.vue"
import AuthService from "@/services/auth/AuthService.js"


const submitData = async (formData) => {
    const service = new AuthService();
    try {
        const response = await service.register(
            formData.username,
            formData.password,
        );
        alert("REGISTRADO")
        router.push("/login")
    } catch (error) {
        console.log(error)
    }
}

</script>

<template>
<FormRegister :onSubmit="submitData" />    
</template>

<style lang="scss">
    
</style>