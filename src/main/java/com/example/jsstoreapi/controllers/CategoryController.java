package com.example.jsstoreapi.controllers;

import com.example.jsstoreapi.CustomizedResponse;
import com.example.jsstoreapi.models.Category;
import com.example.jsstoreapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "https://js-store-react.herokuapp.com/")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity getCategories() {
        CustomizedResponse customizedResponse = new CustomizedResponse("All categories: ", service.getCategories());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity getACategory(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = new CustomizedResponse("All categories: ", Collections.singletonList(service.getACategory(id)));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteACategory(@PathVariable("id") String id) {
        service.deleteACategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value="/movies", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addMovie(@RequestBody Category category) {
        service.addACategory(category);
        return new ResponseEntity(category, HttpStatus.OK);
    }
}
