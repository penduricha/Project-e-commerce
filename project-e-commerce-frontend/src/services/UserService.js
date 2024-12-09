import axios from 'axios';
import AxiosConfig from "@/services/AxiosConfig.js";



// const apiClient = axios.create({
//     baseURL: 'http://localhost:8080/api',
//     headers: {
//         'Content-Type': 'application/json',
//         // Thiết lập header Authorization
//     }
// });

//const apiClientConfig = new ApiClientConfig(apiClient);
const axiosConfig = new AxiosConfig();

export default class UserService{
    constructor(){}

    static getUserByEmailOrPhoneNumber(emailPhoneNumber){
        return axiosConfig.getAPIClient().get(`/users/emailOrPhoneNumber/${emailPhoneNumber}`);
    }

    static createAccount(user) {
        return axiosConfig.getAPIClient().post(`/users`,user, {});
    }

    static updateAccount(emailPhoneNumber,user){
        return axiosConfig.getAPIClient().put(`/users/updateUser/${emailPhoneNumber}`,user,{});
    }
}