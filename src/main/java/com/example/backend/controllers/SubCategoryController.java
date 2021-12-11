package com.example.backend.controllers;

import com.example.backend.Config.TokenService;
import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import com.example.backend.services.SubCategory.SubCategoryService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private TokenService tokenService;

    public SubCategoryController(SubCategoryService subCategoryService,TokenService tokenService) {
        this.subCategoryService = subCategoryService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<Subcategory>>  getAllSubCategories(@RequestHeader(name="Authorization")String token){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(subCategoryService.getAllSubCategories());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<List<Subcategory>> getAllSubCategoriesByIdCategory(@RequestHeader(name="Authorization")String token,String idCategory){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok().body(subCategoryService.getAllSubCategoriesByIdCategory(new ObjectId(String.valueOf(idCategory))));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }

    /*@PostMapping
    public ResponseEntity<Object> addSubCategory(Subcategory subcategory){

        Subcategory su=subCategoryService.saveSubCategory(subcategory);
        if(su==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(su.getIdSubCategory())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }*/

    @PostMapping
    public ResponseEntity<Subcategory> addSubCategory(@RequestHeader(name="Authorization")String token,@RequestBody Subcategory subcategory){
        Subcategory sub=null;
        if(tokenService.verifyTokenAndAdmin(token)){
            try{
                sub=subCategoryService.saveSubCategory(subcategory.getName(),new ObjectId(String.valueOf(subcategory.getIdCategory())));
                return ResponseEntity.ok().body(sub);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity deleteSubCategory(@RequestHeader(name="Authorization")String token, @PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)) {
            subCategoryService.deleteSubCategory(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    //A voir si c'est nécessaire de faire des updates pour une categorie
    /*@PutMapping("/{id}")
    public Subcategory updateSubCategory(@PathVariable String id,@RequestBody Subcategory subcategory){
        return subCategoryService.update(new ObjectId(String.valueOf(id),subcategory);
    }*/
}
