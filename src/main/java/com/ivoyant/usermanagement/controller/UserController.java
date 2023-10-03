package com.ivoyant.usermanagement.controller;

import com.ivoyant.usermanagement.entity.User;
import com.ivoyant.usermanagement.service.UserService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user) throws MessagingException {

        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("The following Error has Occurred During the Registration {}", e.getMessage());
        }
        return null;
    }
}
