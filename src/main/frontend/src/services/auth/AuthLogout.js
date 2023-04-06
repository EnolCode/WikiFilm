import axios from 'axios';
import { baseUrl } from '@/config.js';

export default class AuthLogout {
    #auth = ""; 

    async logout() {
        try {
            localStorage.removeItem("auth");
            const response = await axios.get(`${baseUrl}/api/logout`, { withCredentials: true });
        } catch (error) {
            console.error(error);
        }
    }
}