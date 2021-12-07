package com.example.backend.services;
import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import org.bson.types.ObjectId;

import java.util.List;
public interface AdvertisementService {
    List<Advertisement> findAllAdvertisement();
    Advertisement findOneById(ObjectId id);
    List<Advertisement> findBySousCategorie(int sousCategorie) ;
    void deleteByid(int id);
    Advertisement saveAdvertisement (Advertisement advertisement);
    Advertisement updateAdvertisement(int id ,Advertisement advertisement);
}
