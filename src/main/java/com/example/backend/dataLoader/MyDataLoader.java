package com.example.backend.dataLoader;

import com.example.backend.model.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class MyDataLoader {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository){
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
        this.productRepository = productRepository;
    }


    @PostConstruct
    private void generateData(){
        //User user1=new User("1","A","B","C","Woluwe","1","2",false);
        //userRepository.save(user1);

        /*Category c1=new Category("1","Loisir");
        Category c2=new Category("2","Jardin");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        Subcategory su1= new Subcategory("1",c1,"Telephone");
        Subcategory su2= new Subcategory("2",c1,"Portable");
        Subcategory su3= new Subcategory("3",c2,"Materiel");*/

        /*Product product1 = new Product(1,"a vendre","vélo","petit vélo bleu","campus woluwe",12.01,1,"en vente", 1,LocalDateTime.now());
        productRepository.save(product1);

         */
    }
}
