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

@CrossOrigin(origins = "https://js-store-react.herokuapp.com/")
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        CustomizedResponse response = new CustomizedResponse("A list of all users", userService.getUsers());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getAUser(@PathVariable("id") String id) {
        CustomizedResponse response = null;
        try {
            response = new CustomizedResponse("User with id "+id, Collections.singletonList(userService.getAUser(id)));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/users", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody UserModel user) {
        try {
            user.verify();
            CustomizedResponse response = new CustomizedResponse("User created successfully", Collections.singletonList(userService.addUser(user)));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            CustomizedResponse response = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        CustomizedResponse response = new CustomizedResponse("delete success", null);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
