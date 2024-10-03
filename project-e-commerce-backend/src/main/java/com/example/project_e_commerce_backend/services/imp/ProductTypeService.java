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
    public ProductType saveProductType(ProductType productType) throws JpaSystemException {
        return productTypeRepository.save(productType);
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "productTypeId"));
    }
}
