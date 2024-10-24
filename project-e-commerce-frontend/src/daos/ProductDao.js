import ProductService from "@/services/ProductService.js";
import Product from "@/models/Product.js";

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
        try {
            const response = await ProductService.fetchProducts_By_Event_Explore_Our_Products();
            products = response.data;
            // Map the products directly to a new array of objects
            // products_Clean = products.map(product => ({
            //     product_id: product.product_id,
            //     name: product.name,
            //     image: product.image,
            //     price: product.price,
            //     number_of_discounts: product.number_of_discounts,
            // }));

            return products;
        } catch (error) {
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