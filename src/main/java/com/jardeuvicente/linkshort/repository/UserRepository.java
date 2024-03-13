package com.jardeuvicente.linkshort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardeuvicente.linkshort.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
