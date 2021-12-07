package com.example.backend.controllers;

import com.example.backend.model.Category;
import com.example.backend.model.User;
import com.example.backend.services.Category.CategoryService;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getOneCategory(@PathVariable int id){
        return categoryService.getOneCategory(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("/name")
    public Category getOneCategoryByName(@RequestParam String name){
        return categoryService.getOneCategoryByName(name);
    }

    @PostMapping()
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        Category c=categoryService.saveCategory(category);
        if(c==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.getIdCategory())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(new ObjectId(String.valueOf(id)));
    }
}
