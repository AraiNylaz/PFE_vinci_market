package com.example.backend.dataLoader;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyDataLoader {

    private final UserRepository userRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @PostConstruct
    private void generateData(){
        User user1=new User(1,"A","B","C","Woluwe","1","2",false);
        userRepository.save(user1);
    }
}
