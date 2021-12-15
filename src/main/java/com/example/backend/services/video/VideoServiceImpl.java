package com.example.backend.services.video;

import com.example.backend.model.Picture;
import com.example.backend.model.Video;
import com.example.backend.repository.PictureRepository;
import com.example.backend.repository.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository ;
    public VideoServiceImpl(VideoRepository videoRepository){
        this.videoRepository = videoRepository ;
    }

    @Override
    public Video saveVideo(Video video){
        return videoRepository.save(video);
    }
    @Override
    public void deleteVideoByproduct (ObjectId id){videoRepository.deleteByIdProduct(id);}

    @Override
    public  Video getOneByidProduct(ObjectId id){return videoRepository.getOneVideoByIdProduct(id);}
}
