package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Cart,Long> {

}
