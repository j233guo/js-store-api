package com.example.jsstoreapi.services;

import com.example.jsstoreapi.models.Category;
import com.example.jsstoreapi.models.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategories() {
        return repository.findAll();
    }

    public Optional<Category> getACategory(String id) {
        return repository.findById(id);
    }

    public void deleteACategory(String id) {
        repository.deleteById(id);
    }

    public void addACategory(Category c) {
        repository.insert(c);
    }

    public Category editCategory(String id, Category newCategoryData) {
        Optional<Category> category = repository.findById(id);
        category.get().setImg(newCategoryData.getImg());
        category.get().setName(newCategoryData.getName());
        Category updatedCategory = repository.save(category.get());
        return updatedCategory;
    }
}