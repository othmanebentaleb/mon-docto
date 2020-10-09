package com.mondocto.ms.web;

import com.mondocto.ms.entity.User;
import com.mondocto.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
public class UserRegistrationResource {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Object> registration(@RequestBody @NotNull @Valid User user) {
        User userRegistered = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("{id}")
                                                  .buildAndExpand(userRegistered.getId())
                                                  .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
