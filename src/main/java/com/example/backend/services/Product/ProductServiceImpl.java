package com.example.backend.services.Product;

import com.example.backend.Enums.State;
import com.example.backend.Enums.Status;
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
        UserDTO userDTO=new UserDTO(user.getIdUser(), user.getLastName(), user.getFirstName(), user.getCampus(), user.getPhone(),user.getMail(),user.isAdmin(),user.isBan());
        product.setSeller(userDTO);
        Subcategory subcategory=subCategoriesRepository.findByIdSubCategory(new ObjectId(String.valueOf(product.getIdSubCategory())));
        product.setSubcategory(subcategory);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ObjectId idProduct , Product product){
        Product a = productRepository.findByIdProduct(new ObjectId(String.valueOf(product.getIdProduct())));
        a.setState(product.getState());
        a.setStatusName(product.getStatus().getName());
        a.setTitle(product.getTitle());
        a.setDescription(product.getDescription());
        a.setPrice(product.getPrice());

        a.setIdSubCategory(product.getIdSubCategory());
        a.setSubcategory(subCategoriesRepository.findByIdSubCategory(a.getIdSubCategory()));
        return productRepository.save(a);
    }

    @Override
    public void acceptProduct(ObjectId id){
        Product product=productRepository.findByIdProduct(id);
        product.setState(State.Vente);
        product.setStateName(product.getState().getName());
        productRepository.save(product);
    }

    @Override
    public void sellProduct(ObjectId id){
        Product product=productRepository.findByIdProduct(id);
        product.setState(State.Vendu);
        product.setStateName(product.getState().getName());
        productRepository.save(product);
    }

    @Override
    public void refuseProduct(ObjectId id){
        Product product=productRepository.findByIdProduct(id);
        product.setState(State.REFUSE);
        product.setStateName(product.getState().getName());
        productRepository.save(product);
    }

    @Override
    public void setToDeleteProducts(ObjectId id){
        Product product=productRepository.findByIdProduct(id);
        product.setState(State.SUPPRIME);
        product.setStateName(product.getState().getName());
        productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByState(State state){
        return productRepository.findAllByState(state);
    }


}
