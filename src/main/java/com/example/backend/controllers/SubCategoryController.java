package com.example.backend.controllers;

import com.example.backend.model.Subcategory;
import com.example.backend.services.SubCategory.SubCategoryService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<Subcategory> getAllSubCategories(){
        System.out.println("here");
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{idCategory}")
    public List<Subcategory> getAllSubCategoriesByIdCategory(String idCategory){
        return subCategoryService.getAllSubCategoriesByIdCategory(new ObjectId(String.valueOf(idCategory)));
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
    public Subcategory addSubCategory(@RequestBody Subcategory subcategory){
        Subcategory sub=null;
        System.out.println("here " + subcategory );
        try{
            sub=subCategoryService.saveSubCategory(subcategory.getSubCategoryName(), subcategory.getCateIdentification());
        }catch (Exception e){
            e.printStackTrace();
        }
        return sub;
    }


    @GetMapping("/delete/{id}")
    public void deleteSubCategory(@PathVariable String id){
        subCategoryService.deleteSubCategory(new ObjectId(String.valueOf(id)));
    }

    //A voir si c'est n??cessaire de faire des updates pour une categorie
    /*@PutMapping("/{id}")
    public Subcategory updateSubCategory(@PathVariable String id,@RequestBody Subcategory subcategory){
        return subCategoryService.update(new ObjectId(String.valueOf(id),subcategory);
    }*/
}
