import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default class CartService {
    constructor() {

    }

    static fetchCart_By_Email_Or_PhoneNumber(emailPhoneNumber){
        return apiClient.get(`/cart-item-by-user-email-or-phoneNumber/${emailPhoneNumber}`);
    }

    static deleteCart_By_CartItemId( cartItemId ){
        return apiClient.delete(`/cart-item-by-cart-item-id/${cartItemId}`);
    }

    static addCartItemToCartBy_Email_Or_PhoneNumber( emailPhoneNumber, cartItem ){
        //khúc {} là các cấu hình khác gồm jwt
        return apiClient.post(`/post-cart-item-by-email-or-phone-number/${emailPhoneNumber}`,cartItem, {});
    }

    static getCartItemByEmailOrPhoneNumber_ProductId_Size_Color( emailPhoneNumber, productId, size, color) {
        return apiClient.get(`/find-cart-item-by-productId-size-color/${emailPhoneNumber}/${productId}/${size}/${color}`);
        //return apiClient.get(`find-cart-item-by-productId-size-color/thang123@edu.vn/104/null-size/CFCFCF`);
    }

    static updateQuantityBuy_By_CartItemId( cartItemId, quantityBuy ){
        return apiClient.put(`/update-quantityBuy-by-cartItemId/${cartItemId}/${quantityBuy}`);
    }
}