package com.example.project_e_commerce_backend.insert_data;

import com.example.project_e_commerce_backend.models.User;
import com.example.project_e_commerce_backend.repositories.UserRepository;
//import com.example.project_e_commerce_backend.services.imp.UserService;
import org.springframework.orm.jpa.JpaSystemException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InsertUser {
    private final UserRepository userRepository;

    public InsertUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //phuong thuc POST
    @RequestMapping("/create-list-users")
    public String createListUser() throws JpaSystemException, NoSuchAlgorithmException {
        userRepository.saveAll(getListUsers());
        return "Insert Users Successfully";
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        // Tạo đối tượng MessageDigest với thuật toán SHA-512
        MessageDigest digest = MessageDigest.getInstance("SHA-512");

        // Băm mật khẩu
        byte[] hashBytes = digest.digest(password.getBytes());

        // Chuyển đổi mảng byte sang chuỗi hex
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public List<User> getListUsers() throws NoSuchAlgorithmException {
        List<User> users=new ArrayList<>();

        User user1=new User("nguyenvanan@gmail.com",null,hashPassword("an123"),"An","Nguyen","Van","Nguyen Tat Thanh Street");
        User user2=new User(null,"0981645534",hashPassword("1234"),"Anh","Nguyen","Van","Nguyen Thong Street");
        User user3=new User("maithicuc@edu.vn",null,hashPassword("cuc123"),"Cuc","Mai","Thi","Nguyen Hue Street");
        User user4=new User("maithilan@edu.vn",null,hashPassword("lan123"),"Lan","Mai","Thi","Tran Huy Lieu Street");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }
}
