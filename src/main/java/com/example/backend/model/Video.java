package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("videos")
public class Video {
    @Id
    private String idVideo;

    @Indexed(unique = true)
    @NotNull(message = "You have to insert a value!")
    private ObjectId idProduct;
    @NotNull(message = "You have to insert a value!")
    private String nameVideo;

    public Video(String idVideo, ObjectId idProduct, String nameVideo) {
        this.idVideo = idVideo;
        this.idProduct = idProduct;
        this.nameVideo = nameVideo;
    }
}


