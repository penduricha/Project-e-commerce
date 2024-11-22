package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.Cart;
import com.example.project_e_commerce_backend.models.User;
import com.example.project_e_commerce_backend.repositories.CartRepository;
import com.example.project_e_commerce_backend.repositories.UserRepository;
import com.example.project_e_commerce_backend.services.I_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements I_UserService {
    private final UserRepository userRepository;

    private final CartRepository cartRepository;


    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public User saveUser(User user) throws JpaSystemException {
        User savedUser = userRepository.save(user);
        //Sau khi save xong sẽ save them cái Cart
        Cart cart = new Cart(savedUser);
        cart.getUser().setId(savedUser.getId());
        cartRepository.save(cart);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() throws JpaSystemException {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public User findUserByEmailOrPhoneNumber(String emailOrPhoneNumber) throws JpaSystemException {
        return userRepository.findByEmailOrPhoneNumber(emailOrPhoneNumber, emailOrPhoneNumber);
    }

    @Override
    public User findUserById(Long id) throws JpaSystemException  {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Long getIdByEmailOrPhoneNumber(String emailOrPhoneNumber) throws JpaSystemException {
        User foundUser = findUserByEmailOrPhoneNumber(emailOrPhoneNumber);
        return foundUser.getId();
    }

    @Override
    public void updateUserById(String emailOrPhoneNumber, User user) throws JpaSystemException {
        User foundUser = findUserById(getIdByEmailOrPhoneNumber(emailOrPhoneNumber));
        /*
        {
            "email": null,
            "phoneNumber": "0981645534",
            "password":
            "firstName": "Anh",
            "lastName": "Nguyen",
            "middleName": "Van Ngoc",
            "address" : "Nguyen Thong 13"
        }
         */
        if(foundUser != null){
            foundUser.setEmail(user.getEmail());
            foundUser.setPhoneNumber(user.getPhoneNumber());
            foundUser.setPassword(user.getPassword());
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setMiddleName(user.getMiddleName());
            foundUser.setAddress(user.getAddress());

            userRepository.save(foundUser);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }
}
