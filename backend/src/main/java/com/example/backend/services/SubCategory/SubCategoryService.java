package com.example.backend.services.SubCategory;

import com.example.backend.model.Subcategory;
import org.bson.types.ObjectId;

import java.util.List;

public interface SubCategoryService {

    List<Subcategory> getAllSubCategories();
    List<Subcategory> getAllSubCategoriesByIdCategory(ObjectId id);
    Subcategory saveSubCategory(Subcategory subcategory);
    void deleteSubCategory(ObjectId id);
    Subcategory update(Subcategory subcategory);
}
