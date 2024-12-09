import axios from 'axios';
import AxiosConfig from "@/services/AxiosConfig.js";


// const apiClient = axios.create({
//     baseURL: 'http://localhost:8080/api',
//     headers: {
//         'Content-Type': 'application/json',
//         // Thiết lập header Authorization
//     }
// });
const axiosConfig = new AxiosConfig();

//const apiClientConfig = new ApiClientConfig(apiClient);

export default class ProductService {
    constructor() {

    }

    static fetchProducts_By_Event_Flash_Sales(){
        return axiosConfig.getAPIClient().get(`/products-flash-sales`);
    }

    static fetchProducts_By_Event_Explore_Our_Products(){
        return axiosConfig.getAPIClient().get(`/products-explore-our-products`);
    }

    static fetchProducts_By_Event_Best_Selling(){
        return axiosConfig.getAPIClient().get(`/products-best-selling`);
    }

    static fetchProducts_By_ProductId(productId){
        return axiosConfig.getAPIClient().get(`/products/productId/${productId}`)
    }

    static fetch_Related_Products(productTypeId, productId){
        return axiosConfig.getAPIClient().get(`/products/related/${productTypeId}/${productId}`);
    }
}