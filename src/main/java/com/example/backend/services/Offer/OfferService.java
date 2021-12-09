package com.example.backend.services.Offer;

import com.example.backend.model.Offer;
import org.bson.types.ObjectId;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOffers();

    List<Offer> getAllOffersByIdProduct(ObjectId id);

    Offer saveOffer(Offer offer);

    List<Offer> getAllOffersByIdBuyer(ObjectId idBuyer);

    Offer getOfferWithId(ObjectId id);

    Offer updateOffer(ObjectId idOffer,Offer offer);

    void deleteOffer(ObjectId idOffer);
}
