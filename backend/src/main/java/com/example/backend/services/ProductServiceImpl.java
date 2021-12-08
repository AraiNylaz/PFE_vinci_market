package com.example.backend.services;

import com.example.backend.model.Product;
import com.example.backend.model.Subcategory;
import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SubCategoriesRepository;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SubCategoriesRepository subCategoriesRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,UserRepository userRepository,SubCategoriesRepository subcategory) {
        this.productRepository = productRepository;
        this.userRepository=userRepository;
        this.subCategoriesRepository=subcategory;
    }
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findOneById(ObjectId id) {
        return productRepository.findByIdProduct(id);
    }

    @Override
    public List<Product> findAllProductsByIdSubCategory(ObjectId idSubCategory){
        System.out.println(productRepository.findAllByIdSubCategory(idSubCategory));
        return productRepository.findAllByIdSubCategory(idSubCategory);
    }
    @Override
    public void deleteProduct(ObjectId idProduct){
         productRepository.deleteAllByIdProduct(idProduct);
    }

    @Override
    public Product saveAdvertisement(Product product){
        User user=userRepository.findByIdUser(new ObjectId(String.valueOf(product.getIdSeller())));
        UserDTO userDTO=new UserDTO(user.getIdUser(), user.getLastName(), user.getFirstName(), user.getCampus(), user.getPhone(),user.getMail(),user.isAdmin());
        product.setSeller(userDTO);
        Subcategory subcategory=subCategoriesRepository.findByIdSubCategory(new ObjectId(String.valueOf(product.getIdSubCategory())));
        product.setSubcategory(subcategory);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ObjectId idProduct , Product product){
       /* Product a = productRepository.findByIdAdvertisement(new ObjectId(String.valueOf(id)));
        if(a ==null){
            throw  new InternalError("");
        }
        a.setState(product.getState());
        a.setTitle(product.getTitle());
        a.setDescription(product.getDescription());
        a.setPlace(product.getPlace());
        a.setPrice(product.getPrice());
        a.setState(product.getState());
        return productRepository.save(a);*/
        return null;
    }
}
