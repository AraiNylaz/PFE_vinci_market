package com.example.backend.controllers;


import com.example.backend.model.Picture;
import com.example.backend.model.Video;
import com.example.backend.services.video.VideoService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/videos")
public class VideoController {
    private VideoService videoService;
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{idProduct}")
    public Video getVideoByProduct(@PathVariable String idProduct){
        return videoService.getOneByidProduct(new ObjectId(String.valueOf(idProduct)));
    }
    @DeleteMapping("/{idProduct}")
    public void deleteVideoByProduct(@PathVariable String idProduct){
        videoService.deleteVideoByproduct(new ObjectId(String.valueOf(idProduct)));
    }
    @PostMapping
    public Video addVideo(@RequestBody Video video){
        Video v =null;
        try{
            v=videoService.saveVideo(video);
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

}
