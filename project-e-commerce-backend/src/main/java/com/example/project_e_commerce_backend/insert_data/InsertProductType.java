package com.example.project_e_commerce_backend.insert_data;

import com.example.project_e_commerce_backend.models.ProductType;

import com.example.project_e_commerce_backend.repositories.ProductTypeRepository;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InsertProductType {
    private final ProductTypeRepository productTypeRepository;

    public InsertProductType(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

//    @RequestMapping("/create-list-menus")
    public String createListMenu() throws JpaSystemException, NoSuchAlgorithmException {
        productTypeRepository.saveAll(getListProductType());
        return "Insert Menu Successfully";
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    public List<ProductType> getListProductType(){
        List<ProductType> productTypeList = new ArrayList<>();

        //public ProductType(Long productTypeId,String typeProduct, Long parentId)

        ProductType productType1 = new ProductType("Dog",8L);
        ProductType productType2 = new ProductType("Cat",8L);
        ProductType productType3 = new ProductType("Others",8L);
//        ProductType productType4 = new ProductType("Over 6 ages",7L);
//        ProductType productType5 = new ProductType("Others", 6L);
//        ProductType productType6 = new ProductType("Underwear",3L);
//        ProductType productType7 = new ProductType("Accessory",3L);

        productTypeList.add(productType1);
        productTypeList.add(productType2);
        productTypeList.add(productType3);
//        productTypeList.add(productType4);
//        productTypeList.add(productType5);
//        productTypeList.add(productType6);
//        productTypeList.add(productType7);

        return productTypeList;
    }
}
