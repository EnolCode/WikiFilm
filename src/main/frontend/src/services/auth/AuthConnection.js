import axios from 'axios'
import { baseUrl } from '@/config.js';


export default class AuthConnection {

    #auth = "";
    #role = "";

    async login(username,password) {
        this.#auth = window.btoa(`${username}:${password}`).toString();
        const response = await axios.get(`${baseUrl}/api/login`, {
            headers: {
                Authorizacion: `Basic ${this.#auth}`
            },
            withCredentials: true
        });

        this.#role = response.data.role;
        return this.#role;
    }
}