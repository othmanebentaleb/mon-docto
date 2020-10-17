package com.mondocto.ms.service;

import com.mondocto.ms.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByPhoneNumber(String phoneNumber);
    public List<User> findAllUsers();
    public Optional<User> findById(Long id);
}
