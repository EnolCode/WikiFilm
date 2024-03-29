import axios from 'axios';
import { ref } from "vue";
import { baseUrl } from '@/config.js';

export default class ApiService {
    async get(url){
        try {
            const response = await axios.get(`${baseUrl}${url}`);
            return response.data;
        }catch(error){
            console.log(error);
            throw new Error("Error al obtener datos de la API");
        }
    }

    async delete(url){
        try{
            await axios.delete(`${baseUrl}${url}`);
        }catch(error){
            console.log(error);
            throw new Error("Error al elimiar datos de la API");
        }
    }

    async post(type,formData){
        try{
            await axios({
            method: "POST",
				url: `${baseUrl}/media/upload/${type}`,
				data: formData,
				withCredentials: true,
				headers: {
					"Content-Type": "multipart/form-data",
				},
            });
        }catch(error){
            console.log(error);
            throw new Error("Error al crear datos en la API");
        }
    }
}
