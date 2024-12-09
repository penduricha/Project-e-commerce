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

export default class CartService {


    constructor() {

    }

    static fetchCart_By_Email_Or_PhoneNumber(emailPhoneNumber){
        return axiosConfig.getAPIClient().get(`/cart-item-by-user-email-or-phoneNumber/${emailPhoneNumber}`);
    }

    static deleteCart_By_CartItemId( cartItemId ){
        return axiosConfig.getAPIClient().delete(`/cart-item-by-cart-item-id/${cartItemId}`);
    }

    static addCartItemToCartBy_Email_Or_PhoneNumber( emailPhoneNumber, cartItem ){
        //khúc {} là các cấu hình khác gồm jwt
        return axiosConfig.getAPIClient().post(`/post-cart-item-by-email-or-phone-number/${emailPhoneNumber}`,cartItem, {});
    }

    static getCartItemByEmailOrPhoneNumber_ProductId_Size_Color( emailPhoneNumber, productId, size, color) {
        return axiosConfig.getAPIClient().get(`/find-cart-item-by-productId-size-color/${emailPhoneNumber}/${productId}/${size}/${color}`);
        //return apiClient.get(`find-cart-item-by-productId-size-color/thang123@edu.vn/104/null-size/CFCFCF`);
    }

    static updateQuantityBuy_By_CartItemId( cartItemId, quantityBuy ){
        return axiosConfig.getAPIClient().put(`/update-quantityBuy-by-cartItemId/${cartItemId}/${quantityBuy}`);
    }
}