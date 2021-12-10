package com.example.backend.model;

import com.example.backend.Enums.Campus;
import com.example.backend.Enums.Etat;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Document("products")
public class Product {

    @Id
    private String idProduct;

    @NotNull(message = "You have to insert a value!")
    private String status;

    @NotNull(message = "You have to insert a value!")
    private String title;

    private String description ;

    @NotNull(message = "You have to insert a value!")
    private double price ;

    @NotNull(message = "You have to insert a value!")
    private ObjectId  idSeller;

    private UserDTO seller;


    private Etat state;

    private String stateName;

    @NotNull(message = "You have to insert a value!")
    private ObjectId idSubCategory;

    private Subcategory subcategory;

    private LocalDateTime creationDate ;

    public Product(String idProduct, String status, String title, String description, double price, ObjectId idSeller, UserDTO seller, Etat state,String stateName, ObjectId idSubCategory, Subcategory subcategory, LocalDateTime creationDate) {
        this.idProduct = idProduct;
        this.status = status;
        this.title = title;
        this.description = description;
        this.price = price;
        this.idSeller = idSeller;
        this.seller = seller;
        this.state = state;
        this.stateName=stateName;
        this.idSubCategory = idSubCategory;
        this.subcategory = subcategory;
        this.creationDate = creationDate;
    }
}