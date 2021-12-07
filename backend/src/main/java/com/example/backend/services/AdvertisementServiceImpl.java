package com.example.backend.services;

import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import com.example.backend.repository.AdvertisementRepository;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementRepository advertisementRepository ;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }
    @Override
    public List<Advertisement> findAllAdvertisement() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement findOneById(ObjectId id) {
        return advertisementRepository.findByIdAdvertisement(id);
    }
    @Override
    public List<Advertisement> findBySousCategorie(int sousCategorie){
        return advertisementRepository.findByIdSousCategorie(sousCategorie);
    }
    @Override
    public void deleteByid(int id){
         advertisementRepository.deleteAdvertisementByIdAdvertisement(id);
    }
    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement){
        return advertisementRepository.saveAdvertisement(advertisement);
    }
    @Override
    public Advertisement updateAdvertisement(int id ,Advertisement advertisement){
        Advertisement a = advertisementRepository.findByIdAdvertisement(new ObjectId(String.valueOf(id)));
        if(a ==null){
            throw  new InternalError("");
        }
        a.setState(advertisement.getState());
        a.setTitle(advertisement.getTitle());
        a.setDescription(advertisement.getDescription());
        a.setPlace(advertisement.getPlace());
        a.setPrice(advertisement.getPrice());
        a.setState(advertisement.getState());
        return advertisementRepository.saveAdvertisement(a);
    }
}
