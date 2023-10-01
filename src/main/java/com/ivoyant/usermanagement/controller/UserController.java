package com.ivoyant.usermanagement.controller;

import com.ivoyant.usermanagement.entity.User;
import com.ivoyant.usermanagement.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("registerUser")
     public ResponseEntity<User>  createUser(@RequestBody User user)throws MessagingException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
