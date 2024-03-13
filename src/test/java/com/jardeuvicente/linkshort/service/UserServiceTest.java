package com.jardeuvicente.linkshort.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UserRepository;

@ContextConfiguration
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        String userName = "Jardeu";
        String email = "jardeu219@gmail.com";

        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = this.userService.create(user);

        assertEquals(user, result);
    }
}
