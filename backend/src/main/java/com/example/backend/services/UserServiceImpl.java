package com.example.backend.services;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findOneById(ObjectId id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public User saveUser(User user ){
        return userRepository.save(user );
    }
    @Override
    public boolean checkUser(User user){
        User u = userRepository.findByMail(user.getMail());
        if(u.getPassword() == user.getPassword()){
            return true ;
        }
        return false ;
    }
}
