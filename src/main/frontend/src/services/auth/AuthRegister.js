import axios from "axios";
import { baseUrl } from '@/config.js';

export default class AuthRegister {
    #auth = "";

    async register(username, password) {

        const user = {
            username: username,
            password: password
            roles: [{ id : 2 }]
        };

        const response = await axios.post(`${baseUrl}/api/register`, user);
    }
} 