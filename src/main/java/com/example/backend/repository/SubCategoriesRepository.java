package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubCategoriesRepository extends MongoRepository<Subcategory, ObjectId> {
    List<Subcategory> findAllBy();

    List<Subcategory> findAllByCategory(Category category);

    int countAllBy();

    Subcategory findByName(String name);

    void deleteByIdSubCategory(ObjectId idSubCategory);

    Subcategory findByIdSubCategory(ObjectId id);
}
