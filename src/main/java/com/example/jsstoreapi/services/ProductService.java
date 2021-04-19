package com.example.jsstoreapi.services;

import com.example.jsstoreapi.models.Product;
import com.example.jsstoreapi.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Optional<Product> getAProduct(String id) throws Exception {
        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("Product not found");
        }
        return product;
    }

    public List<Product> getProductsByCategory(String c) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(c));
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products;
    }

    public List<Product> getBestSellers() {
        Query query = new Query();
        query.addCriteria(Criteria.where("bestseller").is("true"));
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products;
    }

    public void deleteAProduct(String id) throws Exception{
        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("Product not found");
        }
        repository.deleteById(id);
    }

    public void addAProduct(Product p) {
        repository.insert(p);
    }

    public Product editProduct(String id, Product newProductData) throws Exception {

        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("Product not found");
        }

        product.get().setImg(newProductData.getImg());
        product.get().setTitle(newProductData.getTitle());
        product.get().setName(newProductData.getName());
        product.get().setPrice(newProductData.getPrice());
        product.get().setCategory(newProductData.getCategory());
        product.get().setQty(newProductData.getQty());
        product.get().setBestseller(newProductData.getBestseller());
        product.get().setDescription(newProductData.getDescription());

        Product updatedProduct = repository.save(product.get());
        return updatedProduct;
    }
}
