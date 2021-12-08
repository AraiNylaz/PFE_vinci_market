package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("offers")
public class Offer {
    @Id
    private String idOffer;
    private ObjectId idProduct;
    private Product product;
    private ObjectId idBuyer;
    private UserDTO buyer;
    private float value;
    private String message;

    public Offer(String idOffer, ObjectId idProduct,Product product, ObjectId idBuyer,UserDTO userDTO , float value, String message) {
        this.idOffer = idOffer;
        this.idProduct = idProduct;
        this.product=product;
        this.idBuyer = idBuyer;
        this.buyer=userDTO;
        this.value = value;
        this.message = message;
    }
}
