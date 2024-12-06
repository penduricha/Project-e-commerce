import axios from 'axios';



const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
        // Thiết lập header Authorization
    }
});

//const apiClientConfig = new ApiClientConfig(apiClient);

export default class UserService{
    constructor(){}

    static getUserByEmailOrPhoneNumber(emailPhoneNumber){
        return apiClient.get(`/users/emailOrPhoneNumber/${emailPhoneNumber}`);
    }

    static createAccount(user) {
        return apiClient.post(`/users`,user, {});
    }

    static updateAccount(emailPhoneNumber,user){
        return apiClient.put(`/users/updateUser/${emailPhoneNumber}`,user,{});
    }
}