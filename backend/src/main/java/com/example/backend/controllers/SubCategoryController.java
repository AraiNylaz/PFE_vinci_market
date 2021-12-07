package com.example.backend.controllers;

import com.example.backend.model.Subcategory;
import com.example.backend.services.SubCategory.SubCategoryService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/subCategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<Subcategory> getAllSubCategories(){
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{idCategory}")
    public List<Subcategory> getAllSubCategoriesByIdCategory(int idCategory){
        return subCategoryService.getAllSubCategoriesByIdCategory(new ObjectId(String.valueOf(idCategory)));
    }

    @PostMapping
    public ResponseEntity<Object> addSubCategory(Subcategory subcategory){

        Subcategory su=subCategoryService.saveSubCategory(subcategory);
        if(su==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(su.getIdSubCategory())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("/delete/{id}")
    public void deleteSubCategory(@PathVariable int id){
        subCategoryService.deleteSubCategory(new ObjectId(String.valueOf(id)));
    }

    @PutMapping
    public Subcategory updateSubCategory(@RequestBody Subcategory subcategory){
        return subCategoryService.update(subcategory);
    }
}
