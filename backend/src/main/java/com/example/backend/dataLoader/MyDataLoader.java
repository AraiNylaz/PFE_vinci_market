package com.example.backend.dataLoader;

import com.example.backend.model.Category;
import com.example.backend.model.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyDataLoader {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository,CategoryRepository categoryRepository){
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
    }

    @PostConstruct
    private void generateData(){
        User user1=new User(1,"A","B","C","Woluwe","1","2",false);
        userRepository.save(user1);
        Category c1=new Category(1,"Loisir");
        Category c2=new Category(2,"Jardin");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
    }
}
