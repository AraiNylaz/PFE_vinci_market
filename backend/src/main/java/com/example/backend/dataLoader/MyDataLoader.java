package com.example.backend.dataLoader;

<<<<<<< Updated upstream
import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import com.example.backend.model.User;
import com.example.backend.repository.CategoryRepository;
=======
import com.example.backend.model.Advertisement;
import com.example.backend.model.User;
import com.example.backend.repository.AdvertisementRepository;
>>>>>>> Stashed changes
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class MyDataLoader {

    private final UserRepository userRepository;
<<<<<<< Updated upstream
    private final CategoryRepository categoryRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository,CategoryRepository categoryRepository){
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
=======
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository ,AdvertisementRepository advertisementRepository){
        this.userRepository=userRepository;
        this.advertisementRepository=advertisementRepository;
>>>>>>> Stashed changes
    }


    @PostConstruct
    private void generateData(){
        User user1=new User(1,"A","B","C","Woluwe","1","2",false);
        userRepository.save(user1);
<<<<<<< Updated upstream
        Category c1=new Category(1,"Loisir");
        Category c2=new Category(2,"Jardin");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        Subcategory su1= new Subcategory(1,1,"Telephone");
        Subcategory su2= new Subcategory(2,1,"Portable");
        Subcategory su3= new Subcategory(3,2,"Materiel");
=======
        Advertisement advertisement1 = new Advertisement(1,"a vendre","vélo","petit vélo bleu","campus woluwe",12.01,1,"en vente", 1,LocalDateTime.now());
        advertisementRepository.save(advertisement1);
>>>>>>> Stashed changes
    }
}
