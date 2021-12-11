package com.example.backend.controllers;

import com.example.backend.Config.TokenService;
import com.example.backend.model.Category;
import com.example.backend.services.Category.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private TokenService tokenService;

    public CategoryController(CategoryService categoryService, TokenService tokenService) {
        this.categoryService = categoryService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestHeader(name="Authorization")String token){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(categoryService.getAllCategories());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneCategory(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(categoryService.getOneCategory(new ObjectId(String.valueOf(id))));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    //pas nécessaire
    /*
    @GetMapping("/name")
    public Category getOneCategoryByName(@RequestParam String name){
        return categoryService.getOneCategoryByName(name);
    }*/

    /*@PostMapping()
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        Category c=categoryService.saveCategory(category);
        if(c==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.getIdCategory())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }*/

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestHeader(name="Authorization")String token,@RequestBody Category category){
        Category c=null;
        if(tokenService.verifyTokenAndAdmin(token)) {
            try {
                c = categoryService.saveCategory(category);
                return ResponseEntity.ok().body(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)) {
            categoryService.deleteCategory(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
