package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.User;
import com.example.project_e_commerce_backend.repositories.UserRepository;
import com.example.project_e_commerce_backend.services.I_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements I_UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws JpaSystemException {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() throws JpaSystemException {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public User findUserByEmailOrPhoneNumber(String emailOrPhoneNumber) {
        return userRepository.findByEmailOrPhoneNumber(emailOrPhoneNumber, emailOrPhoneNumber);
    }

    @Override
    public void updateUserByEmail(String email, User user) {
//        User foundUser = findUserByEmailOrPhoneNumber(email);
//        if(foundProfile!=null){
//            profileRepository.save(profile);
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
//        }
    }
}
