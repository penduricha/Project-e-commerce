package com.example.project_e_commerce_backend.insert_data;

import com.example.project_e_commerce_backend.models.Discount;
import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.ProductTypeRepository;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class InsertProduct {

    private final ProductTypeRepository productTypeRepository;

    public InsertProduct(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @RequestMapping("/create-table-orm-product")
    public String createTable() throws JpaSystemException {
        productTypeRepository.saveAll(getListProductType());
        return "Create Successfully";
    }

    //cach 1
    public List<ProductType> getListProductType(){
        List<ProductType> productTypeList = new ArrayList<>();

        ProductType productType1 = new ProductType(1L,"Sport and Outdoor",null);
        ProductType productType2 = new ProductType(2L,"Electric",null);
        ProductType productType3 = new ProductType(3L,"Cloth Sport",productType1.getProductTypeId());
        ProductType productType4 = new ProductType(4L,"Ball Sport",productType1.getProductTypeId());
        ProductType productType5 = new ProductType(5L,"Game electric",productType2.getProductTypeId());
        ProductType productType6 = new ProductType(6L,"Iphone",productType2.getProductTypeId());

        Product product1 = new Product("Game pad","A game controller, is a handheld device used " +
                "to interact with video games. It typically features a variety of buttons, " +
                "triggers, and analog sticks that allow players to " +
                "control game characters and environments effectively.","Flash Sale");

        Product product2 = new Product("T shirt football","A football T-shirt is a casual garment designed " +
                "specifically for fans, players, or enthusiasts of the sport.","Best Selling Product");

        Product product3 = new Product("Iphone 12","The iPhone 12, released by Apple in October 2020, " +
                "represents a significant upgrade in the " +
                "iPhone lineup with a blend of new technology, design, and features.","Best Selling Product");

        Product product4 = new Product("Ball Football","Typically made from a synthetic outer casing, " +
                "such as polyurethane or PVC, providing durability and resistance to wear." +
                "The interior is filled with air, " +
                "usually with a latex or bladder that maintains shape and pressure.\n", "Flash Sale");

        Product product5 = new Product("Ball Tennis","Designed for various types of play, " +
                "including recreational and professional tennis, " +
                "and they come in different types, " +
                "such as regular duty and extra duty, " +
                "depending on the court surface.",null);

        WareHouse wareHouse1 = new WareHouse(null, "Black",100,
                "https://github.com/penduricha/Image_E_Commerce/blob/master/electric/game_pad_black.png?raw=true",
                1000);

        WareHouse wareHouse7 = new WareHouse(null, "Green",100,
                "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/electric/game_pad_green.jfif",
                1500);

        WareHouse wareHouse2 = new WareHouse("M","Red",200,
                "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/sport-and-outside/t_shirt_M_red.jfif",
                500);

        WareHouse wareHouse3 = new WareHouse("XL","Yellow",300,
                "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/sport-and-outside/t_shirt_M_yellow.jpg",
                550);

        WareHouse wareHouse4 = new WareHouse(null,"Red",1000,
                "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/electric/iphone_12_red.jfif",
                1550);

        WareHouse wareHouse5 = new WareHouse(null,"White",1000,
                "https://github.com/penduricha/Image_E_Commerce/blob/master/electric/iphone_12_white.png?raw=true",
                2550);

        WareHouse wareHouse6 = new WareHouse(null,"White",3000,
                "https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/sport-and-outside/ball_football.jfif",
                450);

        WareHouse wareHouse8 = new WareHouse(null,null,6000,
                "https://github.com/penduricha/Image_E_Commerce/blob/master/sport-and-outside/ball_tennis.png?raw=true",
                450);

        //Discount
        Discount discount1 = new Discount(20);
        Discount discount2 = new Discount(15);
        Discount discount3 = new Discount(40);
        Discount discount4 = new Discount(10);

        //mapping relationship with Product Type
        productType5.getProducts().add(product1);
        product1.setProductType(productType5);

        productType3.getProducts().add(product2);
        product2.setProductType(productType3);

        productType6.getProducts().add(product3);
        product3.setProductType(productType6);

        productType4.getProducts().add(product4);
        product4.setProductType(productType4);

        productType4.getProducts().add(product5);
        product5.setProductType(productType4);

        //mapping relationship with Product WareHouse

        product1.getWareHouseList().add(wareHouse1);
        wareHouse1.setProduct(product1);
        product1.getWareHouseList().add(wareHouse7);
        wareHouse7.setProduct(product1);

        product2.getWareHouseList().add(wareHouse2);
        product2.getWareHouseList().add(wareHouse3);
        wareHouse2.setProduct(product2);
        wareHouse3.setProduct(product2);

        product3.getWareHouseList().add(wareHouse4);
        product3.getWareHouseList().add(wareHouse5);
        wareHouse4.setProduct(product3);
        wareHouse5.setProduct(product3);

        product4.getWareHouseList().add(wareHouse6);
        wareHouse6.setProduct(product4);

        product5.getWareHouseList().add(wareHouse8);
        wareHouse8.setProduct(product5);


        //discount mapping warehouse
        wareHouse4.setDiscount(discount1);
        discount1.setWareHouse(wareHouse4);

        wareHouse5.setDiscount(discount2);
        discount2.setWareHouse(wareHouse5);

        wareHouse6.setDiscount(discount3);
        discount3.setWareHouse(wareHouse6);

        wareHouse7.setDiscount(discount4);
        discount4.setWareHouse(wareHouse7);

        //add Product type
        productTypeList.add(productType1);
        productTypeList.add(productType2);
        productTypeList.add(productType3);
        productTypeList.add(productType4);
        productTypeList.add(productType5);
        productTypeList.add(productType6);

        return productTypeList;
    }

    //cach 2
    public List<ProductType> getMappingProductType(){


        return null;
    }

}
