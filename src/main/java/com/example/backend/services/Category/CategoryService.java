package com.example.backend.services.Category;

import com.example.backend.model.Category;
import org.bson.types.ObjectId;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    Category getOneCategory(ObjectId id);
    Category getOneCategoryByName(String name);
    void deleteCategory(ObjectId id);
    Category saveCategory(Category category);
}
