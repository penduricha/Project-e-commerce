import ProductService from "@/services/ProductService.js";

export default class ProductDao {
    constructor() {

    }

    async getFlashSalesProducts() {
        let products = [];
        try{
            const response = await ProductService.fetchProducts_By_Event_Flash_Sales();
            products = response.data;
            return products;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }

    async getExploreOurProducts() {
        let products = [];
        try{
            const response = await ProductService.fetchProducts_By_Event_Explore_Our_Products();
            products = response.data;
            return products;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }

    async getBestSellingProducts() {
        let products = [];
        try{
            const response = await ProductService.fetchProducts_By_Event_Best_Selling();
            products = response.data;
            return products;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }
}