package com.jardeuvicente.linkshort.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jardeuvicente.linkshort.model.Users;
import com.jardeuvicente.linkshort.service.UsersService;

@RestController
public class UsersController {
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public String registerUser(@RequestBody Users user) {
        return userService.register(user.getEmail(), user.getName());
    }

    @GetMapping("/users/{email}")
    public Users userFindByEmail(@PathVariable String email) throws IOException {
        return userService.userFindByEmail(email);
    }

    @GetMapping("/users/")
    public List<Users> findAllUsers() throws IOException {
        return userService.findAllUsers();
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) throws IOException {
        return userService.deleteUser(id);
    }
}
