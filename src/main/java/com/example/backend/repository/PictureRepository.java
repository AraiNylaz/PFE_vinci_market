package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Picture;
import com.example.backend.model.Subcategory;
import com.example.backend.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
public interface PictureRepository extends PagingAndSortingRepository<Picture, ObjectId> {
    List<Picture>findByIdProduct(ObjectId idProduct) ;
    void deleteAllByIdProduct(ObjectId idProduct) ;
    Picture getPictureByIdPicture(ObjectId idProduct);
}
