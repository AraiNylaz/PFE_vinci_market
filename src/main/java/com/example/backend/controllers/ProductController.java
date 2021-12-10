package com.example.backend.controllers;
import com.example.backend.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.model.Product;
import com.example.backend.services.Product.ProductService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getAllProducts(){
        return productService.findAllProduct();
    }

    @GetMapping("/filter/{idSubCategory}")
    public Iterable<Product> getProductsBySubCategory(@PathVariable String idSubCategory){
        System.out.println("here is " + idSubCategory );
        return productService.findAllProductsByIdSubCategory(new ObjectId(String.valueOf(idSubCategory)));
    }

    @GetMapping("/{idProduct}")
    public Product getProduct(@PathVariable String idProduct){
        return productService.findOneById(new ObjectId(String.valueOf(idProduct)));
    }

    @GetMapping("/delete/{idProduct}")
    public void deleteProduct(@PathVariable("idProduct") String idProduct){
        productService.deleteProduct(new ObjectId(String.valueOf(idProduct)));
    }
    /*@PostMapping
    public ResponseEntity<Void> createAdvertisement(@RequestBody Product product) {
        Product a = productService.saveAdvertisement(product);
        if(a == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.getIdProduct())
                .toUri();
        return ResponseEntity.created(location).build();
    }*/

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Product pro= null;
        try{
            product.setState(State.Debut);
            product.setStateName(product.getState().getName());
            product.setStatusName(product.getStatus().getName());
            pro=productService.saveAdvertisement(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pro;
    }

    @PutMapping ("/{id}")
    public void updateProduct(@PathVariable ("id") String idProduct,@RequestBody Product product) {
        productService.updateProduct(new ObjectId(String.valueOf(idProduct)), product);
    }

    @GetMapping("/valid/{id}")
    public void acceptProduct(@PathVariable String idProduct){
        productService.acceptProduct(new ObjectId(String.valueOf(idProduct)));
    }

    @GetMapping("/sell/{id}")
    public void sellProduct(@PathVariable String idProduct){
        productService.sellProduct(new ObjectId(String.valueOf(idProduct)));
    }

    @GetMapping("refuse/{id}")
    public void refuseProduct(@PathVariable String idProduct){
        productService.refuseProduct(new ObjectId(String.valueOf(idProduct)));
    }

    @GetMapping("setToDelete/{id}")
    public void setToDeleteProducts(@PathVariable String idProduct){
        productService.setToDeleteProducts(new ObjectId(String.valueOf(idProduct)));
    }
}
