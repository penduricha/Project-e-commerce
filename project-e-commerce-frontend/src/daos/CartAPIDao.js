import CartService from "@/services/CarService.js";
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
}