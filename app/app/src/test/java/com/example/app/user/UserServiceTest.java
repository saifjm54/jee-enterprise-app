package com.example.app.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)  // Enables Mockito support
class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock the repository

    @InjectMocks
    private UserService userService;  // Inject mocks into service



    @Test
    void testUserRegistration() {
        User user = new User("saifjmaa54@gmail.com", "password");

        // Mock repository behavior
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call service method
        User registeredUser = userService.register(user);

        // Verify & Assert
        assertNotNull(registeredUser);
        assertEquals("saifjmaa54@gmail.com", registeredUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class)); // Ensure save is called once
    }
}
