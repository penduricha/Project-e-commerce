export default class Cart{
    _productId
    _image
    _name
    _price
    _quantity

    constructor(productId,image, name, price, quantity) {
        this._productId = productId;
        this._image = image;
        this._name = name;
        this._price = price;
        this._quantity = quantity;
    }
}