package com.example.backend.controllers;
import com.example.backend.Config.TokenService;
import com.example.backend.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private TokenService tokenService;

    public ProductController(ProductService productService,TokenService tokenService) {
        this.productService = productService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts(@RequestHeader(name="Authorization")String token){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(productService.findAllProduct());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/filter/{idSubCategory}")
    public ResponseEntity<Iterable<Product>> getProductsBySubCategory(@RequestHeader(name="Authorization")String token,@PathVariable String idSubCategory){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(productService.findAllProductsByIdSubCategory(new ObjectId(String.valueOf(idSubCategory))));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> getProduct(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyToken(token)) {
            return ResponseEntity.ok().body(productService.findOneById(new ObjectId(String.valueOf(idProduct))));

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/delete/{idProduct}")
    public ResponseEntity deleteProduct(@RequestHeader(name="Authorization")String token,@PathVariable("idProduct") String idProduct){
        if(tokenService.verifyToken(token)){
            productService.deleteProduct(new ObjectId(String.valueOf(idProduct)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

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
    public ResponseEntity<Product> addProduct(@RequestHeader(name="Authorization")String token, @RequestBody Product product) {
        Product pro= null;
        if(tokenService.verifyToken(token)){
            try{
                product.setState(State.Debut);
                product.setStateName(product.getState().getName());
                product.setStatusName(product.getStatus().getName());
                pro=productService.saveAdvertisement(product);
                return ResponseEntity.ok().body(pro);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestHeader(name="Authorization")String token,@PathVariable ("id") String idProduct,@RequestBody Product product) {
        if(tokenService.verifyToken(token)){
            Product p=productService.updateProduct(new ObjectId(String.valueOf(idProduct)), product);
            return ResponseEntity.ok().body(p);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/valid/{id}")
    public ResponseEntity acceptProduct(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyTokenAndAdmin(token)){
            productService.acceptProduct(new ObjectId(String.valueOf(idProduct)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/sell/{id}")
    public ResponseEntity sellProduct(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyToken(token)){
            productService.sellProduct(new ObjectId(String.valueOf(idProduct)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("refuse/{id}")
    public ResponseEntity refuseProduct(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyTokenAndAdmin(token)){
            productService.refuseProduct(new ObjectId(String.valueOf(idProduct)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("setToDelete/{id}")
    public ResponseEntity setToDeleteProducts(@RequestHeader(name="Authorization")String token,@PathVariable String idProduct){
        if(tokenService.verifyTokenAndAdmin(token)){
            productService.setToDeleteProducts(new ObjectId(String.valueOf(idProduct)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
