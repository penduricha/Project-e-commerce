import axios from 'axios';
import AxiosConfig from "@/services/AxiosConfig.js";

// const apiClient = axios.create({
//     baseURL: 'http://localhost:8080/api',
//     headers: {
//         'Content-Type': 'application/json',
//     }
// });
const axiosConfig = new AxiosConfig();

export default class WareHouseService{
    constructor() {

    }

    static fetchWareHouses_By_ProductId(productId){
        return axiosConfig.getAPIClient().get(`/warehouses/productId/${productId}`);
    }

    static fetchItemCart_By_ProductId_Size_Color(productId, size, color){
        return axiosConfig.getAPIClient().get(`/cart/productId-size-color/${productId}/${size}/${color}`);
    }

    static fetchItemCart_By_ProductId_Size(productId, size){
        return axiosConfig.getAPIClient().get(`/cart/productId-size/${productId}/${size}`);
    }

    static fetchItemCart_By_ProductId_Color(productId, color){
        return axiosConfig.getAPIClient().get(`/cart/productId-color/${productId}/${color}`);
    }

    static fetchItemCart_By_ProductId_Size_And_Color_Null(productId){
        return axiosConfig.getAPIClient().get(`/cart/productId-size-and-color-null/${productId}`);
    }
}