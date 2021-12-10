package com.example.backend.services.User;

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
    public User findOneByMail(String mail){return userRepository.findByMail(mail);}

    @Override
    public User saveUser(User user ){
        user.setAdmin(false);
        user.setBan(false);
        return userRepository.save(user);
    }
    @Override
    public User checkUser(User user){
        User u = userRepository.findByMail(user.getMail());
        if(u!=null && (u.getPassword().equals(user.getPassword()))){
            return u ;
        }
        return null;
    }

    @Override
    public void switchRole(ObjectId id){
        User u=userRepository.findByIdUser(id);
        u.setAdmin(!u.isAdmin());
        userRepository.save(u);
    }

    @Override
    public User updateUser(ObjectId id,User user){
        User u =userRepository.findByIdUser(id);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setCampus(user.getCampus());
        u.setCampusName(u.getCampus().getName());
        u.setPhone(user.getPhone());
        return userRepository.save(u);
    }

    @Override
    public void deleteUser(ObjectId id){
        userRepository.deleteByIdUser(id);
    }

    @Override
    public void switchBan(ObjectId id){
        User u=userRepository.findByIdUser(id);
        u.setBan(!u.isBan());
        userRepository.save(u);
    }
}