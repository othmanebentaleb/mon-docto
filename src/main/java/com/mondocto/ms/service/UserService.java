package com.mondocto.ms.service;

import com.mondocto.ms.entity.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User findByEmail(String email);
    public User findByPhoneNumber(String phoneNumber);
    public List<User> findAllUsers();
}
