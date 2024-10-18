import ProductTypeService from "@/services/ProductTypeService.js";
import ProductService from "@/services/ProductService.js";


export default class ProductTypeDao {
    constructor(){
       
    }

    async getListProductType_That_ParentId_Null(){
        let productType = [];
        await ProductTypeService.fetchListProductType_That_ParentId_Null().then(response => {
            productType = response.data;
        }).catch(error => {
            console.error(error);
            alert(error);
        });
        return productType;
    }

    async getListProductType_By_ParentId(parentId){
        let productType = [];
        await ProductTypeService.fetchAllProductTypes_ByParentId(parentId).then(response => {
            productType = response.data;
        }).catch(error => {
            console.error(error);
            alert(error);
        });
        return productType;
    }

    async getTypeProductById(productTypeId) {
        try {
            const response = await ProductTypeService.fetchTypeProductById(productTypeId);
            return response.data;
        } catch (error) {
            console.error(error);
            alert(error);
            return null;
            // Trả về null khi có lỗi
        }
    }


}