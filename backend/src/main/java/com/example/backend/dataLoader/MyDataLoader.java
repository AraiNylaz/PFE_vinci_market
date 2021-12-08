package com.example.backend.dataLoader;

import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import com.example.backend.model.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.model.Advertisement;
import com.example.backend.repository.AdvertisementRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class MyDataLoader {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository,CategoryRepository categoryRepository,AdvertisementRepository advertisementRepository){
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
        this.advertisementRepository=advertisementRepository;
    }


    @PostConstruct
    private void generateData(){
        User user1=new User("1","A","B","C","Woluwe","1","2",false);
        userRepository.save(user1);

        /*Category c1=new Category("1","Loisir");
        Category c2=new Category("2","Jardin");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        Subcategory su1= new Subcategory("1",c1,"Telephone");
        Subcategory su2= new Subcategory("2",c1,"Portable");
        Subcategory su3= new Subcategory("3",c2,"Materiel");*/

        Advertisement advertisement1 = new Advertisement(1,"a vendre","vélo","petit vélo bleu","campus woluwe",12.01,1,"en vente", 1,LocalDateTime.now());
        advertisementRepository.save(advertisement1);
    }
}
