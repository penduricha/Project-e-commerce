export default class CartDao{
    constructor() {

    }

    saveToCartLocalStorage(cart){
        const cartString = JSON.stringify(cart);
        localStorage.setItem('cartLocalStorage', cartString);
    }
}