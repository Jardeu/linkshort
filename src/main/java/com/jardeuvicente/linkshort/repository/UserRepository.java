package com.jardeuvicente.linkshort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardeuvicente.linkshort.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    User findByEmail(String email);
}
