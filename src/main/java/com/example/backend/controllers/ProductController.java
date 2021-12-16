package com.example.backend.controllers;
import com.example.backend.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.model.Product;
import com.example.backend.services.Product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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
            ZoneId zid = ZoneId.of("Europe/Paris");
            product.setCreationDate(LocalDate.now(zid));
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
    public void acceptProduct(@PathVariable String id){
        productService.acceptProduct(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("/sell/{id}")
    public void sellProduct(@PathVariable String id){
        productService.sellProduct(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("refuse/{id}")
    public void refuseProduct(@PathVariable String id){
        productService.refuseProduct(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("setToDelete/{id}")
    public void setToDeleteProducts(@PathVariable String id){
        productService.setToDeleteProducts(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("all/notValidate")
    public List<Product> getAllProductEnCoursDeValidation(){
        return productService.getProductsByState(State.Debut);
    }


    private boolean testPrices(double priceMin,double priceMax){
        if(((priceMin != -1 && priceMax!=-1) || (priceMin != -1 && priceMax==-1) || (priceMin==-1 && priceMax!=-1))){
            return true;
        }
        return false;
    }


    @GetMapping("all/toSell")
    public List<Product> getAllProductEnVente(@RequestParam(required = false,defaultValue = " ")String idSubCategory,@RequestParam(required = false,defaultValue = "-1.0") double priceMin,@RequestParam(required = false,defaultValue = "-1.0") double priceMax){
        System.out.println(idSubCategory +" " +priceMin +" " +priceMax);
        if(idSubCategory.equals(" ") && testPrices(priceMin,priceMax)){
            return productService.getProductsByStateEnVenteAndFiltereByPrice(State.Vente,priceMin,priceMax);
        }

        if(!idSubCategory.equals(" ") && testPrices(priceMin,priceMax)  ){
            return productService.getProductsFilteredByPriceAndSubCategory(State.Vente,new ObjectId(String.valueOf(idSubCategory)),priceMin,priceMax);
        }

        if(!idSubCategory.equals(" ") && priceMin ==-1 && priceMax==-1){
            return productService.getProductByStateAndCategorie(State.Vente,new ObjectId(String.valueOf(idSubCategory)));
        }

        return productService.getProductsByState(State.Vente);
    }

    @GetMapping("all/sold")
    public List<Product> getAllProductVendu(){
        return productService.getProductsByState(State.Vendu);
    }


    @GetMapping("user/{id}")
    public List<Product> getAllProductFromOneUser(@PathVariable String id){
        return productService.getAllProductsFromOneUser(new ObjectId(String.valueOf(id)));
    }


}
