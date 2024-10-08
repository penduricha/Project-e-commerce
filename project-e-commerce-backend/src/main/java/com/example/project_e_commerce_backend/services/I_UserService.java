package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.User;

import java.util.List;

public interface I_UserService {
    public User saveUser(User user);

    public List<User> getAllUsers();

    public User findUserByEmailOrPhoneNumber(String emailOrPhoneNumber);

    public Long getIdByEmailOrPhoneNumber(String emailOrPhoneNumber);

    public User findUserById(Long id);

    public void updateUserById(String emailOrPhoneNumber, User user);

}
