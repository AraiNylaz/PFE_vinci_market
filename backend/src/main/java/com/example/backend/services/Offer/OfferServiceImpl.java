package com.example.backend.services.Offer;

import com.example.backend.model.Offer;
import com.example.backend.repository.OfferRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{

   private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.getAllBy();
    }

    @Override
    public List<Offer> getAllOffersByIdProduct(ObjectId id) {
        return offerRepository.getAllByIdProduct(id);
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return null;
    }

    @Override
    public List<Offer> getAllOffersByIdBuyer(ObjectId idBuyer) {
        return getAllOffersByIdBuyer(idBuyer);
    }

    @Override
    public Offer getOfferWithId(ObjectId id) {
        return getOfferWithId(id);
    }

    @Override
    public Offer updateOffer(ObjectId idOffer, Offer offer) {
        Offer o= offerRepository.getOfferByIdOffer(idOffer);
        o.setMessage(offer.getMessage());
        o.setValue(offer.getValue());
        offerRepository.save(o);
        return o;
    }

    @Override
    public void deleteOffer(ObjectId idOffer) {
         offerRepository.deleteByIdOffer(idOffer);
    }
}
