package com.example.backend.services.Picture;

import com.example.backend.model.Picture;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;


public interface PictureService {
    List<Picture>getAllByproduct(ObjectId id);
    void deletePicturesByproduct (ObjectId id);
}
