package com.mondocto.ms.web;

import com.mondocto.ms.entity.User;
import com.mondocto.ms.exceptions.ResourceAleradyExistsException;
import com.mondocto.ms.exceptions.UserNotFoundException;
import com.mondocto.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
public class UserRegistrationResource {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationResource.class);

    @PostMapping("/user")
    public ResponseEntity<Object> registration(@RequestBody @Valid User user) {
        Optional<User> userRegistered;
        if (!user.getEmail().isEmpty() && !user.getPhoneNumber().isEmpty()) {
            logger.error("Email '"+ user.getEmail()+"' or phone number '" + user.getPhoneNumber() + "' exist alerady");
            this.throwExistingResourceException(user.getEmail(), user.getPhoneNumber());
        }
        userRegistered = Optional.ofNullable(userService.createUser(user));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRegistered.get().getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with id : " + id + " not found");
        }
        return ResponseEntity.ok(user.get());
    }
    
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /*******                        ********
     ********    Private Methods    ********
     ********                       *******/
    private void throwExistingResourceException(String email, String phoneNumber) {
        Optional<User> userRegistered = userService.findByEmail(email);
        userRegistered.ifPresent(userFound -> {
            throw new ResourceAleradyExistsException("Email : " + userFound.getEmail() + " Already exists");
        });
        userRegistered = userService.findByPhoneNumber(phoneNumber);
        userRegistered.ifPresent(userFound -> {
            throw new ResourceAleradyExistsException("Phone number : " + userFound.getPhoneNumber() + " Aleready exists");
        });
    }


}
