import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default class WareHouseService{
    constructor() {

    }

    static fetchWareHouses_By_ProductId(productId){
        return apiClient.get(`/warehouses/productId/${productId}`);
    }

    static fetchItemCart_By_ProductId_Size_Color(productId, size, color){
        return apiClient.get(`/cart/productId-size-color/${productId}/${size}/${color}`);
    }

    static fetchItemCart_By_ProductId_Size(productId, size){
        return apiClient.get(`/cart/productId-size/${productId}/${size}`);
    }

    static fetchItemCart_By_ProductId_Color(productId, color){
        return apiClient.get(`/cart/productId-color/${productId}/${color}`);
    }
}