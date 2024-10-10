import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default class UserService{
    constructor(){}

    static getUserByEmailOrPhoneNumber(emailPhoneNumber){
        return apiClient.get(`/users/emailOrPhoneNumber/${emailPhoneNumber}`);
    }

    static createAccount(user) {
        return apiClient.post('/users',user, {});
    }
}