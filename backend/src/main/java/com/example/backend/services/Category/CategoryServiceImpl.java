package com.example.backend.services.Category;

import com.example.backend.model.Category;
import com.example.backend.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllBy();
    }

    @Override
    public Category getOneCategory(ObjectId id) {
        return categoryRepository.findByIdCategory(id);
    }

    @Override
    public Category getOneCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void deleteCategory(ObjectId id) {
        categoryRepository.deleteByIdCategory(id);
    }

    @Override
    public Category saveCategory(Category category){
        category.setIdCategory(categoryRepository.countAllBy()+1);
        return categoryRepository.save(category);
    }

}
