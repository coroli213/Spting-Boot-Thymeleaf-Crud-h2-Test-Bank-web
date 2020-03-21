package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}
