package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.ProductType;

import java.util.List;

public interface I_ProductTypeService {

    public ProductType saveProductType(ProductType productType);

    public List<ProductType> saveListProductType(List<ProductType> productTypes);

    public List<ProductType> getAllProductTypes();

    public ProductType findProductTypeById(Long productTypeId);

    public List<ProductType> getAllProductTypes_ByParentId_Null();

    public ProductType findProductType_ByTypeProduct(String typeProduct);

    public Long getProductTypeId_By_TypeProduct(String typeProduct);

    public List<ProductType> getAllProductTypes_ByParentId(Long parentId);

    String getTypeProductById(Long productTypeId);
}
