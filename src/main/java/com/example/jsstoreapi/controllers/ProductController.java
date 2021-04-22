package com.example.jsstoreapi.controllers;

import com.example.jsstoreapi.CustomizedResponse;
import com.example.jsstoreapi.models.Product;
import com.example.jsstoreapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        var customizedResponse = new CustomizedResponse("All Products", service.getProducts());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getAProduct(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Product with id "+id, Collections.singletonList(service.getAProduct(id)));
        } catch (Exception ex) {
            customizedResponse = new CustomizedResponse(ex.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/category")
    public ResponseEntity getProductsByCategory(@RequestParam(value="c") String c) {
        var customizedResponse = new CustomizedResponse(c, service.getProductsByCategory(c));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/bestsellers")
    public ResponseEntity getBestSellers() {
        var customizedResponse = new CustomizedResponse("Best Sellers", service.getBestSellers());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteAProduct(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            service.deleteAProduct(id);
            customizedResponse = new CustomizedResponse("Product "+id+" was deleted successfully", null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addProduct(@RequestBody Product p) {
        try {
            p.verify();
            var response = new CustomizedResponse("product added successfully", Collections.singletonList(service.addAProduct(p)));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            var response = new CustomizedResponse(ex.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/products/{id}", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity editProduct(@PathVariable("id") String id, @RequestBody Product newProduct) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Product with ID "+id+" was updated successfully", Collections.singletonList(service.editProduct(id, newProduct)));
        } catch (Exception ex) {
            customizedResponse = new CustomizedResponse(ex.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

}
