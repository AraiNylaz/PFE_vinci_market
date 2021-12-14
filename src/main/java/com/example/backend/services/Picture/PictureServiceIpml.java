package com.example.backend.services.Picture;

import com.example.backend.model.Offer;
import com.example.backend.model.Picture;
import com.example.backend.repository.PictureRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureServiceIpml implements  PictureService{
    private final PictureRepository pictureRepository ;

    public PictureServiceIpml(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository ;
    }
    @Override
    public List<Picture> getAllByproduct(ObjectId id) {
        return pictureRepository.findByIdProduct(id);
    }
}
