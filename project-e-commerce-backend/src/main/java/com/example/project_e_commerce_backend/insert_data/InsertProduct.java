package com.example.project_e_commerce_backend.insert_data;

import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.ProductRepository;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsertProduct {
    private final ProductRepository productRepository;

    //private final WomenFashion_Skirt_Normal womenFashionSkirtNormal = new WomenFashion_Skirt_Normal();

    //private final WomenFashion_Skirt_Modern womenFashionSkirtModern = new WomenFashion_Skirt_Modern();

    //private final WomenFashion_Skirt_Sun_Protection womenFashionShirtSunProtection = new WomenFashion_Skirt_Sun_Protection();

    public InsertProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/create-list-products")
    public String create_list_product() throws JpaSystemException {
        productRepository.saveAll(getListProduct());
        return "Success";
    }



    public List<Product> getListProduct(){
        String linkGithub = "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/";

        List<Product> listProduct = new ArrayList<>();

        Product product = new Product("Modern clothes","Love is beautiful");

        WareHouse warehouse_1 = new WareHouse(null,"Blue",30,linkGithub+"men's-fashion/clothes/modern.png",150);
        WareHouse warehouse_2 = new WareHouse(null,"Red",30,linkGithub+"men's-fashion/clothes/modern.png",150);

        product.getWareHouseList().add(warehouse_1);
        warehouse_1.setProduct(product);
        product.getWareHouseList().add(warehouse_2);
        warehouse_2.setProduct(product);

        ProductType productType = new ProductType();
        productType.setProductTypeId(50L);

        product.setProductType(productType);

        listProduct.add(product);

        return listProduct;
    }
}
