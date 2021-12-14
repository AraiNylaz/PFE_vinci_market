package com.example.backend.controllers;


import com.example.backend.model.Offer;
import com.example.backend.model.Picture;
import com.example.backend.services.Picture.PictureService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/pictures")
public class PictureController {
    private PictureService pictureService;
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{idProduct}")
    public List<Picture> getAllPictureByProduct(@PathVariable String idProduct){
        return pictureService.getAllByproduct(new ObjectId(String.valueOf(idProduct)));
    }

    @DeleteMapping("/{idProduct}")
    public void deleteAllPicturesByproduct(@PathVariable String idProduct){
        pictureService.deletePicturesByproduct(new ObjectId(String.valueOf(idProduct)));
    }
}
