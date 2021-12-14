package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("pictures")
public class Picture {
    @Id
    private String idPicture;
    @NotNull(message = "You have to insert a value!")
    private ObjectId idProduct;
    @NotNull(message = "You have to insert a value!")
    private String namePicture;

    public Picture(String idPicture, ObjectId idProduct, String namePicture) {
        this.idPicture = idPicture;
        this.idProduct = idProduct;
        this.namePicture = namePicture;
    }
}
