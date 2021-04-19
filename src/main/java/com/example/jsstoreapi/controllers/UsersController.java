package com.example.jsstoreapi.controllers;

import com.example.jsstoreapi.CustomizedResponse;
import com.example.jsstoreapi.models.UserModel;
import com.example.jsstoreapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        var response = new CustomizedResponse("A list of all users", userService.getUsers());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getAUser(@PathVariable("id") String id) {
        var response = new CustomizedResponse("User with id "+id, Collections.singletonList(userService.getAUser(id)));
        return new ResponseEntity(response, HttpStatus.OK);

    }

    @PostMapping(value="/users", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody UserModel user) {
        try {
            user.verify();
            var response = new CustomizedResponse("User created successfully", Collections.singletonList(userService.addUser(user)));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            var response = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        var response = new CustomizedResponse("delete success", null);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
