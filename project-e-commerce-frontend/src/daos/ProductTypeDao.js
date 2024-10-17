import ProductTypeService from "@/services/ProductTypeService.js";


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

    getProductTypeId_By_Name_TypeProduct(typeProduct){
        
    }
}