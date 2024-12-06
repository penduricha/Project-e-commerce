// AuthService.js
import axios from 'axios';

class AuthService {
    constructor() {
        this.apiClient = axios.create({
            baseURL: 'http://localhost:8080',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    }

    // Phương thức đăng nhập
    async login(username, password) {
        const token = btoa(`${username}:${password}`); // Mã hóa thông tin đăng nhập
        this.apiClient.defaults.headers['Authorization'] = `Basic ${token}`; // Thiết lập header Authorization

        try {
            const response = await this.apiClient.post('/login', {
                username,
                password
            });
            return response.data; // Trả về dữ liệu phản hồi
        } catch (error) {
            throw error.response ? error.response.data : error.message; // Xử lý lỗi
        }
    }
}

export default new AuthService();