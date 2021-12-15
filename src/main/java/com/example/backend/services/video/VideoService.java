package com.example.backend.services.video;

import com.example.backend.model.Picture;
import com.example.backend.model.Video;
import org.bson.types.ObjectId;

public interface VideoService {
    void deleteVideoByproduct (ObjectId id);
    Video getOneByidProduct(ObjectId id);
    Video saveVideo (Video video);
}
