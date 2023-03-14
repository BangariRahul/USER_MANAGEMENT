package com.example.UserManagement.service;

import com.example.UserManagement.dao.UserRepository;
import com.example.UserManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String getAllUser() {

        String str = userRepository.findAll().toString();
        return str;
    }

    public void saveuser(User newuser) {
        userRepository.save(newuser);
    }

    public  void deleteuser(int id) {
        userRepository.deleteById(id);
    }
}
