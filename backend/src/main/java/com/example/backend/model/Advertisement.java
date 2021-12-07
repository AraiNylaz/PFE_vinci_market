package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("advertisements")
public class Advertisement {

    @Id
    private int idAdvertisement;
    private String status;
    private String title;
    private String description ;
    private String place;
    private double price ;
    private int  idSeller ;
    private String  state;
    private LocalDateTime creationDate ;

    public Advertisement(int idAdvertisement, String status, String title, String description, String place, double price, int idSeller, String state, LocalDateTime creationDate) {
        this.idAdvertisement = idAdvertisement;
        this.status = status;
        this.title = title;
        this.description = description;
        this.place = place;
        this.price = price;
        this.idSeller = idSeller;
        this.state = state;
        this.creationDate = creationDate;
    }
}