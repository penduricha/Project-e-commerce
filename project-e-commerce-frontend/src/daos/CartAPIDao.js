import CartService from "@/services/CartService.js";
import UserService from "@/services/UserService.js";


export default class CartAPIDao {
    constructor() {

    }

    async getCartItemsBy_Email_Or_PhoneNumber( emailPhoneNumber ) {
        let carts = [];
        try{
            const response = await CartService.fetchCart_By_Email_Or_PhoneNumber(emailPhoneNumber);
            carts = response.data;
            return carts;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }

    async deleteCartItem_By_CartItemId( cartItemId ){
        try{
            return await CartService.deleteCart_By_CartItemId(cartItemId);
        }catch(error){
            console.error(error);
            alert(error);
            return 0;
        }
    }

    async addCartItem_By_Email_Or_PhoneNumber( emailPhoneNumber, cartItem ){
        try {
            //thực hiện POST
            await CartService.addCartItemToCartBy_Email_Or_PhoneNumber(emailPhoneNumber,cartItem);
            return 1;
        } catch (error) {
            console.error('Error: ', error);
            return 0;
        }
    }

    async getCartItem_By_Email_Or_PhoneNumber_Size_Color( emailPhoneNumber, productId, size, color ){
        let cartItem = {};
        if(!size){
            size = 'null-size';
        }

        if(!color){
            color = 'null-color';
        }

        console.log('Input is: ',emailPhoneNumber,productId,size,color);

        try{
            color = color.slice(1);
            const response = await CartService.getCartItemByEmailOrPhoneNumber_ProductId_Size_Color( emailPhoneNumber, productId, size, color );
            cartItem = response.data;
            console.log('Found cart item by productId size color: ',cartItem);
            return cartItem;
        }catch(error){
            console.error('Error: ', error);
            return {}
        }
    }

    async updateQuantityBuy_By_CartItemId( cartItemId, quantityBuy ) {
        try{
            await CartService.updateQuantityBuy_By_CartItemId(cartItemId, quantityBuy);
            return 1;
        }catch(error){
            console.error('Error: ', error);
            return 0;
        }
    }
}