package com.example.backend.services.Picture;

import com.example.backend.model.Picture;
import org.bson.types.ObjectId;

import java.util.List;


public interface PictureService {
    List<Picture>getAllByproduct(ObjectId id);
    void deletePicturesByproduct (ObjectId id);
    Picture getOneByid(ObjectId id);
    Picture savePicture (Picture picture);

}
