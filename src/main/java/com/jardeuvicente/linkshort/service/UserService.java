package com.jardeuvicente.linkshort.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(String email, String name) {
        User user = new User();

        user.setName(name);

        if (userRepository.findByEmail(email) != null) {
            return "This email already used for another user";
        }

        user.setEmail(email);
        userRepository.save(user);

        return email;
    }

    public User userFindByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        List<User> user = userRepository.findAll();

        return user;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);

        return "Usuário deletado com sucesso";
    }
}
