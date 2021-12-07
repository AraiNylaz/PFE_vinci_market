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
        user.setIdUser(userRepository.countAllBy()+1);
        user.setAdmin(false);
        return userRepository.save(user);
    }
    @Override
    public boolean checkUser(User user){
        User u = userRepository.findByMail(user.getMail());
        if(u.getPassword() == user.getPassword()){
            return true ;
        }
        return false ;
    }

    @Override
    public void switchRole(ObjectId id){
        User u=userRepository.findByIdUser(id);
        u.setAdmin(!u.isAdmin());
    }

    @Override
    public User updateUser(User user){
        User u =userRepository.findByIdUser(new ObjectId(String.valueOf(user.getIdUser())));
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setCampus(user.getCampus());
        u.setPhone(user.getPhone());
        u.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(ObjectId id){
        userRepository.deleteByIdUser(id);
    }
}