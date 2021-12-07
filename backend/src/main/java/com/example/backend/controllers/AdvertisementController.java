package com.example.backend.controllers;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import com.example.backend.services.AdvertisementService;
import com.example.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
>>>>>>> Stashed changes

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {
    private AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
    @GetMapping
    public Iterable<Advertisement> allAdvertisement(){
        return advertisementService.findAllAdvertisement();
    }
    @GetMapping("/filter/{idSousCategorie}")
    public Iterable<Advertisement> getAdvertisementSousCategorie(@PathVariable("idSousCategorie") int sousCategorie){
        return advertisementService.findBySousCategorie(sousCategorie);
    }
    @DeleteMapping("/delete/{idAdvertisement}")
    public void deleteAdvertisement(@PathVariable("idAdvertisement") int idAdvertisement){
        advertisementService.deleteByid(idAdvertisement);
    }
    @PostMapping
    public ResponseEntity<Void> createAdvertisement(@RequestBody Advertisement advertisement) {
        Advertisement a = advertisementService.saveAdvertisement(advertisement);
        if(a == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.getIdAdvertisement())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping ("/{id}")
    public void updateCommentaire(@PathVariable ("id") int id,@RequestBody Advertisement advertisement) {
        advertisementService.updateAdvertisement(id,advertisement);
    }
    }
