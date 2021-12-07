package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("offers")
public class Offer {
    @Id
    private int idOffer;
    private int idProduct;
    private int idBuyer;
    private float value;
    private String message;

    public Offer(int idOffer, int idProduct, int idBuyer, float value, String message) {
        this.idOffer = idOffer;
        this.idProduct = idProduct;
        this.idBuyer = idBuyer;
        this.value = value;
        this.message = message;
    }
}
