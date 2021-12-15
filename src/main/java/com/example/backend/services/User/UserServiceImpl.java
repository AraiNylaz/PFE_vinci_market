package com.example.backend.services.User;

import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> liste=userRepository.findAll();
        List<UserDTO> listeDTO=new ArrayList<>();
        for (User u: liste) {
            UserDTO userdto=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
            listeDTO.add(userdto);
        }
        return listeDTO;
    }

    @Override
    public UserDTO findOneById(ObjectId id) {

        User u= userRepository.findByIdUser(id);
        UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
        return userDTO;
    }

    @Override
    public UserDTO findOneByMail(String mail){
        User u= userRepository.findByMail(mail);
        UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
        return userDTO;
    }

    @Override
    public UserDTO saveUser(User user ){
        user.setAdmin(false);
        user.setBan(false);
        User u=userRepository.save(user);
        UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
        return userDTO;
    }
    @Override
    public UserDTO checkUser(User user){
        User u = userRepository.findByMail(user.getMail());
        if(u!=null && (u.getPassword().equals(user.getPassword()))){
            UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
            return userDTO;
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
    public UserDTO updateUser(ObjectId id,User user){
        System.out.println("user : "+ user);
        User u =userRepository.findByIdUser(id);
        System.out.println("u : "+ u);

        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setCampus(user.getCampus());
        u.setPhone(user.getPhone());
        User user2=userRepository.save(u);
        UserDTO userDTO=new UserDTO(user2.getIdUser(),user2.getLastName(),user2.getFirstName(),user2.getCampus(),user2.getPhone(),user2.getMail(),user2.isAdmin(),user2.isBan());
        return userDTO;
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