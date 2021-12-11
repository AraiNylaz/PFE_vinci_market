package com.example.backend.services.User;

import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO findOneById(ObjectId id);
    UserDTO findOneByMail(String mail);
    UserDTO checkUser(User user);
    UserDTO saveUser (User user);
    void switchRole(ObjectId id);
    UserDTO updateUser(ObjectId id,User user);
    void deleteUser(ObjectId id);
    void switchBan(ObjectId id);
}
