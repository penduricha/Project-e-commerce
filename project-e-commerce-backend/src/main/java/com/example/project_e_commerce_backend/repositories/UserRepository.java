package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailOrPhoneNumber(String email,String phoneNumber);
}
