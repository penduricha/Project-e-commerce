package com.example.project_e_commerce_backend.data_product;

import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.ProductRepository;
import com.example.project_e_commerce_backend.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertProduct {
    private final ProductRepository productRepository;

    @Autowired
    public InsertProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/insert-one-product")
    public String insert() throws JpaSystemException {
        productRepository.save(getProduct());
        return "Insert success";
    }

    public ProductType getProductType(){
        ProductType productType = new ProductType();
        productType.setProductTypeId(73L);
        return productType;
    }

    public Product getProduct(){
        Product product = new Product("Cooker Mitsubishi","Cook meat and rice.");
        product.setProductType(getProductType());
        String image = "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/electric/household/cooker-mitsubishi.png";
        WareHouse wareHouse = new WareHouse(null, null, 100,image, 200);
        product.getWareHouseList().add(wareHouse);
        wareHouse.setProduct(product);
        return product;
    }
}
