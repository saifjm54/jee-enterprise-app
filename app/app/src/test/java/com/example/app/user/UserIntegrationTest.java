package com.example.app.user;

import com.example.app.AppApplication;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = AppApplication.class)
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void givenUserRepository_whenSaveUser_thenSuccess() {
        User user = userRepository.save(new User(
                "saifjmaa54@gmail.com",
                "password"
        ));

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertTrue(foundUser.checkPassword("password"));
    }
}
