package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.User;

import com.example.project_e_commerce_backend.services.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) throws JpaSystemException {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws JpaSystemException  {
        return userService.getAllUsers();
    }

    @GetMapping("/users/emailOrPhoneNumber/{emailOrPhoneNumber}")
    public ResponseEntity<User> findUserByEmailOrPhoneNumber(@PathVariable String emailOrPhoneNumber) {
        try {
            User user = userService.findUserByEmailOrPhoneNumber(emailOrPhoneNumber);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (JpaSystemException e) {
            // Handle the JpaSystemException as appropriate
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
