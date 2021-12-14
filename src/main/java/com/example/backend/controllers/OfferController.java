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

@CrossOrigin(origins = "*")
@RestController
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

    @GetMapping("/all/product/{idProduct}")
    public List<Offer> getAllOffersByIdProduct(@PathVariable String idProduct){
        return offerService.getAllOffersByIdProduct(new ObjectId(String.valueOf(idProduct)));
    }

   /* @PostMapping
    public ResponseEntity<Object> addOffer(@RequestBody Offer offer){
        Offer o=offerService.saveOffer(offer);
        if(o==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(o.getIdOffer())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }*/

    @PostMapping
    public Offer addOffer(@RequestBody Offer offer){
        Offer o=null;
        try{
            o=offerService.saveOffer(offer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }

    @GetMapping("/users/{idBuyer}")
    public List<Offer> getAllOffersByIdBuyer(String idBuyer){
        return offerService.getAllOffersByIdBuyer(new ObjectId(String.valueOf(idBuyer)));
    }

    @GetMapping("/{idOffer}")
    public Offer getOfferById(@PathVariable String idOffer){
        return offerService.getOfferWithId(new ObjectId(String.valueOf(idOffer)));
    }

    @GetMapping("/delete/{id}")
    public void deleteOffer(@PathVariable int id){
        offerService.deleteOffer(new ObjectId(String.valueOf(id)));
    }
}
