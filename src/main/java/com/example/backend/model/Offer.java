package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("offers")
public class Offer {
    @Id
    private String idOffer;

    @NotNull(message = "You have to insert a value!")
    private ObjectId idBuyer;

    private UserDTO buyer;

    @NotNull(message = "You have to insert a value!")
    private ObjectId idProduct;

    @NotNull(message = "You have to insert a value!")
    private float value;

    private String message;

    public Offer(String idOffer, ObjectId idProduct, ObjectId idBuyer,UserDTO buyer , float value, String message) {
        this.idOffer = idOffer;
        this.idProduct = idProduct;
        this.idBuyer = idBuyer;
        this.buyer=buyer;
        this.value = value;
        this.message = message;
    }
}
