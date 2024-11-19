export default class CartDto {
    _productId;
    _name;
    _size;
    _color;
    _quantity;


    constructor(productId, name, size, color, quantity) {
        this._productId = productId;
        this._name = name;
        this._size = size;
        this._color = color;
        this._quantity = quantity;
    }

    setQuantity(quantity){
        this._quantity = quantity;
    }
}