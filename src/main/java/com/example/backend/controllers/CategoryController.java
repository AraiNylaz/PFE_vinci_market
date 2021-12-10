package com.example.backend.controllers;

import com.example.backend.model.Category;
import com.example.backend.services.Category.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
    public Category getOneCategory(@PathVariable String id){
        return categoryService.getOneCategory(new ObjectId(String.valueOf(id)));
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
    public Category addCategory(@RequestBody Category category){
        Category c=null;
        try{
            c=categoryService.saveCategory(category);
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    @GetMapping("/delete/{id}")
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(new ObjectId(String.valueOf(id)));
    }
}
