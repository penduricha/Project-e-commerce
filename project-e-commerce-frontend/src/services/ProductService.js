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

    static fetchProducts_By_Event_Explore_Our_Products(){
        return apiClient.get(`/products-explore-our-products`);
    }

    static fetchProducts_By_Event_Best_Selling(){
        return apiClient.get(`/products-best-selling`);
    }
}