package com.jardeuvicente.linkshort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardeuvicente.linkshort.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByName(String name);

    Users findByEmail(String email);
}
