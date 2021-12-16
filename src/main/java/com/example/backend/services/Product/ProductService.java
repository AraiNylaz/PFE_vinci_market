package com.example.backend.services.Product;
import com.example.backend.Enums.State;
import com.example.backend.model.Product;
import com.example.backend.model.Subcategory;
import org.bson.types.ObjectId;

import java.util.List;
public interface ProductService {
    List<Product> findAllProduct();

    Product findOneById(ObjectId id);

    List<Product> findAllProductsByIdSubCategory(ObjectId id) ;
    void deleteProduct(ObjectId id);
    Product saveAdvertisement (Product product);
    Product updateProduct(ObjectId id , Product product);
    void acceptProduct(ObjectId id);
    void sellProduct(ObjectId id);
    void refuseProduct(ObjectId id);
    void setToDeleteProducts(ObjectId id);
    List<Product> getProductsByState(State state);
    List<Product> getProductsByStateEnVenteAndFiltereByPrice(State state,double priceMin,double priceMax);
    List<Product> getAllProductsFromOneUser(ObjectId id);
    List<Product> getProductByStateAndCategorie(State state, ObjectId idSubCategory);
    List<Product> getProductsFilteredByPriceAndSubCategory(State state,ObjectId idSubCategory,double priceMin,double priceMax);
}
