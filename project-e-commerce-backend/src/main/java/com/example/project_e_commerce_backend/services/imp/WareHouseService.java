package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.dtos.WareHouseDto;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.ProductRepository;
import com.example.project_e_commerce_backend.repositories.WareHouseRepository;
import com.example.project_e_commerce_backend.services.I_WarehouseService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseService implements I_WarehouseService {

    private final WareHouseRepository wareHouseRepository;

    private final ProductRepository productRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository, ProductRepository productRepository) {
        this.wareHouseRepository = wareHouseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<WareHouse> getAllWarehouses() {
        return List.of();
        //return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<WareHouseDto> getWareHousesByProduct_ProductId(Long productId) throws JpaSystemException  {
        if(productRepository.findProductByProductId(productId) != null)
            return wareHouseRepository.getWareHousesByProduct_ProductId(productId);
        else
            return null;
    }


}
