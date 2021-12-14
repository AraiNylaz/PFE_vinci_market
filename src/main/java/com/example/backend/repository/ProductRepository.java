package com.example.backend.repository;

import com.example.backend.Enums.State;
import com.example.backend.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findAll();

    Product findByIdProduct(ObjectId id);
    List<Product> findAllByIdSubCategory(ObjectId idSubCategory);
    void deleteAllByIdProduct(ObjectId id);
    List<Product> findAllByState(State state);

}