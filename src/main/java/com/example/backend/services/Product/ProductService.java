package com.example.backend.services.Product;
import com.example.backend.model.Product;
import org.bson.types.ObjectId;

import java.util.List;
public interface ProductService {
    List<Product> findAllProduct();

    Product findOneById(ObjectId id);

    List<Product> findAllProductsByIdSubCategory(ObjectId id) ;
    void deleteProduct(ObjectId id);
    Product saveAdvertisement (Product product);
    Product updateProduct(ObjectId id , Product product);
}
