package com.example.backend.controllers;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import com.example.backend.Enums.Etat;
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
            product.setState(Etat.Debut);
            product.setStateName(product.getState().getName());
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
    }
