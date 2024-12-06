import axios from 'axios';
//import {createRouter as router} from "vue-router";


const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
        // Thiết lập header Authorization
    }
});

// apiClient.interceptors.response.use(
//     response => response,
//     error => {
//         if (error.response && error.response.status === 401) {
//             // Chuyển hướng đến trang login
//             this.$router.push('/login'); // Đường dẫn đến trang login
//             return Promise.reject(error);
//         }
//         return Promise.reject(error);
//     }
// );

export default class ProductTypeService {
    constructor() {}

    // Lấy danh sách các loại sản phẩm có parentId là null
    static fetchListProductType_That_ParentId_Null() {
        return apiClient.get(`/productTypes-parentId-null`);
    }

    // Lấy tất cả các loại sản phẩm theo parentId
    static fetchAllProductTypes_ByParentId(parentId) {
        return apiClient.get(`/productTypes/parentId/${parentId}`);
    }

    // Lấy loại sản phẩm theo ID
    static fetchTypeProductById(productTypeId) {
        return apiClient.get(`/productTypes/get-typeProduct-by-productTypeId/${productTypeId}`);
    }

    // Lấy loại sản phẩm theo productTypeId
    static fetchProductType_By_ProductTypeId(productTypeId) {
        return apiClient.get(`/productTypes/productTypeId/${productTypeId}`);
    }
}