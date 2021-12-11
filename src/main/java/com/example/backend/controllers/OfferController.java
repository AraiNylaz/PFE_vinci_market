package com.example.backend.controllers;


import com.example.backend.Config.TokenService;
import com.example.backend.model.Offer;
import com.example.backend.model.User;
import com.example.backend.services.Offer.OfferService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private TokenService tokenService;

    public OfferController(OfferService offerService,TokenService tokenService) {
        this.tokenService = tokenService;
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers(@RequestHeader(name="Authorization")String token){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(offerService.getAllOffers());

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/all/product/{idProduct}")
    public ResponseEntity<List<Offer>> getAllOffersByIdProduct(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(offerService.getAllOffersByIdProduct(new ObjectId(String.valueOf(idProduct))));

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<Offer> addOffer(@RequestHeader(name="Authorization")String token,@RequestBody Offer offer){
        Offer o=null;
        if(tokenService.verifyToken(token)){
            try{
                o=offerService.saveOffer(offer);
                return ResponseEntity.ok().body(o);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users/{idBuyer}")
    public ResponseEntity<List<Offer>> getAllOffersByIdBuyer(@RequestHeader(name="Authorization")String token,String idBuyer){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(offerService.getAllOffersByIdBuyer(new ObjectId(String.valueOf(idBuyer))));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{idOffer}")
    public ResponseEntity<Offer> getOfferById(@RequestHeader(name="Authorization")String token,@PathVariable String idOffer){
        Offer o=null;
        if(tokenService.verifyToken(token)){
           o=offerService.getOfferWithId(new ObjectId(String.valueOf(idOffer)));
            return ResponseEntity.ok().body(o);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@RequestHeader(name="Authorization")String token,@PathVariable int id){
        if(tokenService.verifyToken(token)) {
            offerService.deleteOffer(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
