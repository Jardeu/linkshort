package com.jardeuvicente.linkshort.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jardeuvicente.linkshort.model.Users;
import com.jardeuvicente.linkshort.repository.UsersRepository;

@ContextConfiguration
@SpringBootTest
public class UsersServiceTest {
    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UsersService userService;

    @Test
    public void testRegisterUser() {
        String userName = "Jardeu";
        String email = "jardeu219@gmail.com";

        Users user = new Users();
        user.setName(userName);
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        String result = userService.register(userName, email);

        assertEquals(email, result);
    }
}
