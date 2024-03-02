package com.jardeuvicente.linkshort.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jardeuvicente.linkshort.model.Users;
import com.jardeuvicente.linkshort.repository.UsersRepository;

@Service
public class UsersService {
    private final UsersRepository userRepository;

    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(String email, String name) {
        Users user = new Users();

        user.setName(name);

        if (userRepository.findByEmail(email) != null) {
            return "This email already used for another user";
        }

        user.setEmail(email);
        userRepository.save(user);

        return email;
    }

    public Users userFindByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Users> findAllUsers() {
        List<Users> user = userRepository.findAll();

        return user;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);

        return "Usuário deletado com sucesso";
    }
}
