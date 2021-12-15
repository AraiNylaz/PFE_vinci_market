package com.example.backend.repository;

import com.example.backend.model.Picture;
import com.example.backend.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, ObjectId> {
    void deleteByIdProduct(ObjectId idProduct) ;

    Video getOneVideoByIdProduct(ObjectId idProduct);
}
