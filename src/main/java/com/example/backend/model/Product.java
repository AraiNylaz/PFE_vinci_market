package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("products")
public class Product {

    @Id
    private String idProduct;
    private String status;
    private String title;
    private String description ;
    private String place;
    private double price ;
    private ObjectId  idSeller;
    private UserDTO seller;
    private String  state;
    private ObjectId idSubCategory;
    private Subcategory subcategory;
    private LocalDateTime creationDate ;

    public Product(String idProduct, String status, String title, String description, String place, double price, ObjectId idSeller, UserDTO seller, String state, ObjectId idSubCategory, Subcategory subcategory, LocalDateTime creationDate) {
        this.idProduct = idProduct;
        this.status = status;
        this.title = title;
        this.description = description;
        this.place = place;
        this.price = price;
        this.idSeller = idSeller;
        this.seller = seller;
        this.state = state;
        this.idSubCategory = idSubCategory;
        this.subcategory = subcategory;
        this.creationDate = creationDate;
    }
}