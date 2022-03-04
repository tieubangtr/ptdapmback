package com.example.managementlibrary.repository;

import com.example.managementlibrary.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findByToken(String token);
}
