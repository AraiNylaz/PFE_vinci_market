package com.example.backend.model;

import com.example.backend.Enums.State;
import com.example.backend.Enums.Status;
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
    private Status status;

    private String statusName;

    @NotNull(message = "You have to insert a value!")
    private String title;

    private String description ;

    @NotNull(message = "You have to insert a value!")
    private float price;

    @NotNull(message = "You have to insert a value!")
    private ObjectId  idSeller;

    private UserDTO seller;


    private State state;

    private String stateName;

    @NotNull(message = "You have to insert a value!")
    private ObjectId idSubCategory;

    private Subcategory subcategory;

    private LocalDateTime creationDate ;

    public Product(String idProduct, Status status,String statusName, String title, String description, float price, ObjectId idSeller, UserDTO seller, State state, String stateName, ObjectId idSubCategory, Subcategory subcategory, LocalDateTime creationDate) {
        this.idProduct = idProduct;
        this.status = status;
        this.statusName=statusName;
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