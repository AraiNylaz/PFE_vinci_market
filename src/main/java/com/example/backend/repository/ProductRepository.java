package com.example.backend.repository;

import com.example.backend.Enums.State;
import com.example.backend.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findAll();

    Product findByIdProduct(ObjectId id);
    List<Product> findAllByIdSubCategory(ObjectId idSubCategory);
    void deleteAllByIdProduct(ObjectId id);
    List<Product> findAllByState(State state);

    //float findFirstByPriceOrderByPriceDesc();

    Product findFirstByStateOrderByPriceDesc(State state);

    //Product findFirstByByOrderByPricePriceDesc();

    @Query("{$and:[{state:?0},{price:{$gte:?1}},{price:{$lte:?2}}]}")
    List<Product> findAllByState(State state,double priceMin,double priceMax);

    List<Product> findAllByIdSeller(ObjectId id);

    List<Product> findAllByStateAndIdSubCategory(State state,ObjectId idSubCategory);

    @Query("{$and:[{state:?0},{idSubCategory:?1},{price:{$gte:?2}},{price:{$lte:?3}}]}")
    List<Product> findAllByStateAndPriceAndSubCategory(State state,ObjectId idSubCategory,double priceMin,double priceMax);

}