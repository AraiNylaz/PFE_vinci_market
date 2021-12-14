package com.example.backend.repository;

import com.example.backend.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    List<User> findAll();

    int countAllBy();

    User findByIdUser(ObjectId id);

    User findByMail(String mail);

    void deleteByIdUser(ObjectId id);
}
