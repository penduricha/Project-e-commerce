export default class ProductType {
    _productTypeId
    _typeProduct
    _parentId
    
    constructor(productTypeId, typeProduct, parentId){
        this._productTypeId = productTypeId;
        this._typeProduct = typeProduct;
        this._parentId = parentId;
    }

    setTypeProduct(typeProduct){
        this._typeProduct = typeProduct;
    }
}