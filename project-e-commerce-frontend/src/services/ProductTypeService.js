import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default class ProductTypeService{

    constructor(){

    }

    static fetchListProductType_That_ParentId_Null(){
        return apiClient.get(`/productTypes-parentId-null`);
    }
    ///productTypes/parentId/{parentId}

    static fetchAllProductTypes_ByParentId(parentId){
        return apiClient.get(`/productTypes/parentId/${parentId}`);
    }
}