

export default class CartDao {
    constructor() {}
    //local storage with list JSON javascript

    saveToCartLocalStorage(listCarts) {
        // Chuyển đổi mảng thành chuỗi JSON trước khi lưu
        localStorage.setItem('cartLocalStorage', JSON.stringify(listCarts));
    }

    getListCartLocalStorage() {
        // Kiểm tra nếu có dữ liệu trong localStorage
        const cartData = localStorage.getItem('cartLocalStorage');
        if (!cartData) {
            return []; // Trả về mảng rỗng nếu không có dữ liệu
        } else {
            // Chuyển đổi chuỗi JSON về mảng
            return JSON.parse(cartData);
        }
    }

    updateQuantityLocalStorage_In_Cart(listCarts, newProductAddCart){
        const itemCart = listCarts.filter(l =>
            l.productId === newProductAddCart.productId &&
            l.size === newProductAddCart.size &&
            l.color === newProductAddCart.color
        )
        console.log('Found in local storage: ',itemCart);

        if(itemCart.length > 0) {
            itemCart[0].quantityBuy = newProductAddCart.quantityBuy;
            console.log(itemCart[0]);
            //listCarts.push(itemCart[0]);
            this.saveToCartLocalStorage(listCarts);
        }
    }

    deleteCartItemLocalStorage(listCarts, index){
        listCarts.splice(index, 1);
        this.removeLocalStorage();
        this.saveToCartLocalStorage(listCarts);
    }

    updateQuantityLocalStorage(listCarts, newProductAddCart) {
        const itemCart = listCarts.filter(l =>
            l.productId === newProductAddCart.productId &&
            l.size === newProductAddCart.size &&
            l.color === newProductAddCart.color
        )
        console.log('Found in local storage: ',itemCart);

        if(itemCart.length > 0){
            itemCart[0].quantityBuy += newProductAddCart.quantityBuy;
            console.log(itemCart[0]);
            //listCarts.push(itemCart[0]);
            this.saveToCartLocalStorage(listCarts);
            //alert("Updated quantity product to cart.");
        }else{
            //add new
            listCarts.push(newProductAddCart);
            this.saveToCartLocalStorage(listCarts);
            //alert("Added product to cart.");
        }
        //lam xong get lai
    }

    // getLengthCartLocalStorage(){
    //     return this.getLengthCartLocalStorage().length;
    // }

    removeLocalStorage() {
        localStorage.removeItem('cartLocalStorage');
    }
}