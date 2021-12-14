package com.example.backend.services.Offer;

import com.example.backend.model.Offer;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.repository.OfferRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{

   private final OfferRepository offerRepository;
   private final UserRepository userRepository;
   private final ProductRepository productRepository;

    public OfferServiceImpl(OfferRepository offerRepository,UserRepository userRepository,ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
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
        User user=userRepository.findByIdUser(offer.getIdBuyer());
        UserDTO userDTO=new UserDTO(user.getIdUser(), user.getLastName(), user.getFirstName(), user.getCampus(), user.getPhone(),user.getMail(),user.isAdmin());
        offer.setBuyer(userDTO);
        //Product p=productRepository.findByIdProduct(offer.getIdProduct());
        //offer.setProduct(p);
        System.out.println(offer);
        Offer o=offerRepository.save(offer);
        System.out.println(o);
        return o;
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
    public void deleteOffer(ObjectId idOffer) {
         offerRepository.deleteByIdOffer(idOffer);
    }
}
