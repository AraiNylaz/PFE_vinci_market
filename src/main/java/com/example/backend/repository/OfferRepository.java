package com.example.backend.repository;

import com.example.backend.model.Offer;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OfferRepository extends PagingAndSortingRepository<Offer, ObjectId> {

    List<Offer> getAllBy();
    List<Offer> getAllByIdProduct(ObjectId idProduct);
    Offer getOfferByIdOffer(ObjectId id);
    List<Offer> getAllByIdBuyer(ObjectId idBuyer);
    void deleteByIdOffer(ObjectId idOffer);

}
