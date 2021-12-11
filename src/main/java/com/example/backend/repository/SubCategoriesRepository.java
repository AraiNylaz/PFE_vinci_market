package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import com.example.backend.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubCategoriesRepository extends MongoRepository<Subcategory,ObjectId> {
    List<Subcategory> findAllBy();

    List<Subcategory> findAllByCategory(Category category);

    Subcategory findByName(String name);

    void deleteByIdSubCategory(ObjectId idSubCategory);

    Subcategory findByIdSubCategory(ObjectId id);
}
