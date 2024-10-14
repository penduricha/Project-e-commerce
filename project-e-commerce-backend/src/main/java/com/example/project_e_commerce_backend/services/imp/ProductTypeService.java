package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.repositories.ProductTypeRepository;
import com.example.project_e_commerce_backend.services.I_ProductTypeService;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductTypeService implements I_ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ProductType saveProductType(ProductType productType) throws JpaSystemException{
        return productTypeRepository.save(productType);
    }

    @Override
    public List<ProductType> saveListProductType(List<ProductType> productTypes) throws JpaSystemException {
        return productTypeRepository.saveAll(productTypes);
    }

    @Override
    public List<ProductType> getAllProductTypes() throws JpaSystemException {
        return productTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "productTypeId"));
    }

    @Override
    public ProductType findProductTypeById(Long productTypeId) throws JpaSystemException {
        return productTypeRepository.findByProductTypeId(productTypeId);
    }


    @Override
    public List<ProductType> getAllProductTypes_ByParentId_Null() throws JpaSystemException {
        return productTypeRepository.findProductTypesByParentIdNull();
    }

    @Override
    public ProductType findProductType_ByTypeProduct(String typeProduct) throws JpaSystemException {
        return productTypeRepository.findByTypeProduct(typeProduct);
    }

    @Override
    public List<ProductType> getAllProductTypes_ByParentId(Long parentId) throws JpaSystemException {
        return productTypeRepository.findProductTypesByParentId(parentId);
    }
}
