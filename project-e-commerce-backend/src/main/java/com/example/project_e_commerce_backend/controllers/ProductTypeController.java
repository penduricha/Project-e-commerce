package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.services.imp.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping("/list-productTypes")
    public List<ProductType> saveListProductType(@RequestBody List<ProductType> productTypes) throws JpaSystemException {
        return productTypeService.saveListProductType(productTypes);
    }

    @PostMapping("/productTypes")
    public ProductType saveProductType(@RequestBody ProductType productType) throws JpaSystemException {
        return productTypeService.saveProductType(productType);
    }

    @GetMapping("/productTypes")
    public List<ProductType> getAllProductTypes() throws JpaSystemException {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping("/productTypes-parentId-null")
    public List<ProductType> getAllProductTypes_ByParentId_Null() throws JpaSystemException {
        return productTypeService.getAllProductTypes_ByParentId_Null();
    }

    @GetMapping("/productTypes/productTypeId/{productTypeId}")
    public ProductType findProductTypeById(@PathVariable Long productTypeId) throws JpaSystemException {
        return productTypeService.findProductTypeById(productTypeId);
    }

    @GetMapping("/productTypes/typeProduct/{typeProduct}")
    public ProductType findProductTypeTypeProduct(@PathVariable String typeProduct) throws JpaSystemException {
        return productTypeService.findProductType_ByTypeProduct(typeProduct);
    }

    @GetMapping("/productTypes/parentId/{parentId}")
    public List<ProductType> getAllProductTypes_ByParentId(@PathVariable Long parentId) throws JpaSystemException {
        return productTypeService.getAllProductTypes_ByParentId(parentId);
    }
}
