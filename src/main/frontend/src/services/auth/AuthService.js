import AuthConnection from "./AuthConnection";
import AuthRegister from "./AuthRegister";
import AuthLogout from "./AuthLogout";

export default class AuthService {
    #conn;
    #reg;
    #logout;

    constructor() {
        this.#conn = new AuthConnection();
        this.#reg = new AuthRegister();
        this.#logout = new AuthLogout();
    }

    async login(username, password) {
        return await this.#conn.login(username, password);
    }

    async register(username, password) {
         await this.#reg.register(username, password);
    }

    async logout() {
        await this.#logout.logout();
    }
}