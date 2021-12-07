package com.example.backend.repository;

import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, ObjectId> {
    List<Advertisement> findAll();

    Advertisement findByIdAdvertisement(ObjectId id);
}