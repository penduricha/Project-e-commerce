export default class Product{
    _productId
    _name
    _image
    _price
    _numberOfDiscount


    constructor(productId, name, image, price, numberOfDiscount) {
        this._productId = productId;
        this._name = name;
        this._image = image;
        this._price = price;
        this._numberOfDiscount = numberOfDiscount;
    }
}