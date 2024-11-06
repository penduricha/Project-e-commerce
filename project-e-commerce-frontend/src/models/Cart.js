export default class Cart{
    _productId
    _image
    _name
    _size
    _color
    _price
    _quantity

    constructor(productId, size, color ,image, name, price, quantity) {
        this._productId = productId;
        this._image = image;
        this._size = size;
        this._color = color;
        this._name = name;
        this._price = price;
        this._quantity = quantity;
    }
}