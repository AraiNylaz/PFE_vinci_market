package com.example.backend.controllers;


import com.example.backend.model.Offer;
import com.example.backend.model.User;
import com.example.backend.services.Offer.OfferService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @GetMapping("/{idProduct}")
    public List<Offer> getAllOffersByIdProduct(@PathVariable int id){
        return offerService.getAllOffersByIdProduct(new ObjectId(String.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity<Object> addOffer(@RequestBody Offer offer){
        Offer o=offerService.saveOffer(offer);
        if(o==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(o.getIdOffer())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{idBuyer}")
    public List<Offer> getAllOffersByIdBuyer(int idBuyer){
        return offerService.getAllOffersByIdBuyer(new ObjectId(String.valueOf(idBuyer)));
    }

    @GetMapping("/one/{idOffer}")
    public Offer getOfferById(@PathVariable int idOffer){
        return offerService.getOfferWithId(new ObjectId(String.valueOf(idOffer)));
    }

    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable int id,Offer offer){
        return offerService.updateOffer(new ObjectId(String.valueOf(id)),offer);
    }

    @GetMapping("/delete/{id}")
    public void deleteOffer(@PathVariable int id){
        offerService.deleteOffer(new ObjectId(String.valueOf(id)));
    }
}
