package com.example.jsstoreapi.controllers;

import com.example.jsstoreapi.CustomizedResponse;
import com.example.jsstoreapi.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://js-store-react.herokuapp.com/")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value="/auth", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity login(@RequestBody UserModel user) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            CustomizedResponse response = new CustomizedResponse("You logged in successfully", null);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (BadCredentialsException ex){
            CustomizedResponse response = new CustomizedResponse("Your log in credentials are not correct", null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
