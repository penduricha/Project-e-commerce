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


}