package com.jardeuvicente.linkshort.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UserRepository;
import com.jardeuvicente.linkshort.service.exception.DataBindingViolationException;
import com.jardeuvicente.linkshort.service.exception.ObjectNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user = this.userRepository.save(user);

        return user;
    }

    public List<User> findAllUsers() {
        List<User> user = userRepository.findAll();

        return user;
    }

    public User findById(Long id) {
        Objects.requireNonNull(id, "Id can't be null!");

        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "User not found!"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        newUser.setPassword(user.getPassword());

        return this.userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Id can't be null!");

        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Unable to delete as there are related entities!");
        }
    }
}
