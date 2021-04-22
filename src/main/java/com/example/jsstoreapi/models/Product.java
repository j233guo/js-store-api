package com.example.jsstoreapi.models;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String id;
    private String img;
    private String title;
    private String name;
    private double price;
    private String category;
    private int qty;
    private String bestseller;
    private String description;

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getBestseller() {
        return bestseller;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBestseller(String bestseller) {
        this.bestseller = bestseller;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void verify() throws Exception {
        if (img == null) {
            throw new Exception("image is empty");
        }
        if (title == null) {
            throw new Exception("title is empty");
        }
        if (name == null) {
            throw new Exception("name is empty");
        }
        if (price == 0) {
            throw new Exception("price is empty");
        }
        if (category == null) {
            throw new Exception("category is empty");
        }
        if (bestseller == null) {
            throw new Exception("bestseller is empty");
        }
        if (description == null) {
            throw new Exception("description is empty");
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", bestseller='" + bestseller + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
