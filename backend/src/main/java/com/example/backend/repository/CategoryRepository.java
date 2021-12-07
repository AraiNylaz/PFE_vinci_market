package com.example.backend.repository;

import com.example.backend.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, ObjectId> {
    Category findByIdCategory(ObjectId id);

    List<Category> findAllBy();

    Category findByName(String name);

    void deleteByIdCategory(ObjectId id);
}
