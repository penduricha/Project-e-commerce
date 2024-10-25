export default class Warehouse {
    _wareHouseId
    _size
    _color
    _quantity
    _image
    _price
    _numberOfDiscount


    constructor(wareHouseId, size, color, quantity, image, price, numberOfDiscount) {
        this._wareHouseId = wareHouseId;
        this._size = size;
        this._color = color;
        this._quantity = quantity;
        this._image = image;
        this._price = price;
        this._numberOfDiscount = numberOfDiscount;
    }
}