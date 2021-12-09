package com.example.backend.services.User;

import com.example.backend.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findOneById(ObjectId id);
    User findOneByMail(String mail);
    User checkUser(User user);
    User saveUser (User user);
    void switchRole(ObjectId id);
    User updateUser(ObjectId id,User user);
    void deleteUser(ObjectId id);
    void switchBan(ObjectId id);
}
