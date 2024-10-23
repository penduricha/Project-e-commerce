export default class Product{
    _productId
    _name
    _image
    _price
    _numberOfDiscount
    _nameEventPurchasing


    constructor(productId, name, image, price, numberOfDiscount, nameEventPurchasing) {
        this._productId = productId;
        this._name = name;
        this._image = image;
        this._price = price;
        this._numberOfDiscount = numberOfDiscount;
        this._nameEventPurchasing = nameEventPurchasing;
    }
}