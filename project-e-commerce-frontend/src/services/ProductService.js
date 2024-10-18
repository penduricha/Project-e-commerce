import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default class ProductService {
    constructor() {

    }

    static fetchProducts_By_Event_Flash_Sales(){
        return apiClient.get(`/products-flash-sales`);
    }
}