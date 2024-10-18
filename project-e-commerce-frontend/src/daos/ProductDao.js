import ProductService from "@/services/ProductService.js";

export default class ProductDao {
    constructor() {

    }

    async getFlashSalesProducts() {
        try{
            const response = await ProductService.fetchProducts_By_Event_Flash_Sales();
            return response.data;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }
}