package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User register(User user) {

        if(user != null){
            return userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("User entity cannot be null");
        }
    }
}
