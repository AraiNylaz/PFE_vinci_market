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
        List<User> list = userRepository.findAllBy();
        List<UserDTO> listDTO=new ArrayList<>();
        for (User u: list) {
            UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getCampusName(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
            listDTO.add(userDTO);
        }

        return listDTO;
    }

    @Override
    public UserDTO findOneById(ObjectId id) {
        User u=userRepository.findByIdUser(id);
        UserDTO userDTO=null;
        if(u!=null) {
            userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getCampusName(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
        }
        return userDTO;
    }

    @Override
    public UserDTO findOneByMail(String mail){
        User u=userRepository.findByMail(mail);
        UserDTO userDTO=null;
        if(u!=null) {
            userDTO = new UserDTO(u.getIdUser(), u.getLastName(), u.getFirstName(), u.getCampus(), u.getCampusName(), u.getPhone(), u.getMail(), u.isAdmin(), u.isBan());
        }
        return userDTO;
    }

    @Override
    public UserDTO saveUser(User user ){
        user.setAdmin(false);
        user.setBan(false);
        userRepository.save(user);
        UserDTO userDTO=new UserDTO(user.getIdUser(),user.getLastName(),user.getFirstName(),user.getCampus(),user.getCampusName(),user.getPhone(),user.getMail(),user.isAdmin(),user.isBan());
        return userDTO;
    }
    @Override
    public UserDTO checkUser(User user){
        User u = userRepository.findByMail(user.getMail());
        if(u!=null && (u.getPassword().equals(user.getPassword())) && user.isBan()!=true){
            UserDTO userDTO=new UserDTO(u.getIdUser(),u.getLastName(),u.getFirstName(),u.getCampus(),u.getCampusName(),u.getPhone(),u.getMail(),u.isAdmin(),u.isBan());
            return userDTO ;
        }
        return null;
    }

    @Override
    public void switchRole(ObjectId id){
        User u=userRepository.findByIdUser(id);
        if(u!=null){
            u.setAdmin(!u.isAdmin());
            userRepository.save(u);
        }
    }

    @Override
    public UserDTO updateUser(ObjectId id, User user){
        User u =userRepository.findByIdUser(id);
        UserDTO userDTO=null;
        if(u!=null) {
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPassword(user.getPassword());
            u.setCampus(user.getCampus());
            u.setCampusName(u.getCampus().getName());
            u.setPhone(user.getPhone());
            userRepository.save(u);
            userDTO = new UserDTO(u.getIdUser(), u.getLastName(), u.getFirstName(), u.getCampus(), u.getCampusName(), u.getPhone(), u.getMail(), u.isAdmin(), u.isBan());
        }
        return userDTO;
    }

    @Override
    public void deleteUser(ObjectId id){
        userRepository.deleteByIdUser(id);
    }

    @Override
    public void switchBan(ObjectId id){
        User u=userRepository.findByIdUser(id);
        if(u!=null){
            u.setBan(!u.isBan());
            userRepository.save(u);
        }

    }
}