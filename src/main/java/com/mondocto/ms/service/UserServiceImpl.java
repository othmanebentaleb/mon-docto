package com.mondocto.ms.service;

import com.mondocto.ms.entity.User;
import com.mondocto.ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if(Optional.of(user).isPresent()){
          return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
