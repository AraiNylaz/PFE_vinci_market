package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubCategoriesRepository extends PagingAndSortingRepository<Subcategory, ObjectId> {
    List<Subcategory> findAllBy();

    List<Subcategory> findAllByIdCategory(ObjectId id);

    Subcategory findByName(String name);

    void deleteByIdSubCategory(ObjectId idSubCategory);

    Subcategory findByIdSubCategory(ObjectId id);
}
