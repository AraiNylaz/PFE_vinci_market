package com.example.backend.services.SubCategory;

import com.example.backend.model.Category;
import com.example.backend.model.Subcategory;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.SubCategoriesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    private final SubCategoriesRepository subCategoriesRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoriesRepository subCategoriesRepository,CategoryRepository categoryRepository) {
        this.subCategoriesRepository = subCategoriesRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Subcategory> getAllSubCategories() {
        return subCategoriesRepository.findAllBy();
    }

    @Override
    public List<Subcategory> getAllSubCategoriesByIdCategory(ObjectId id) {
        Category c=categoryRepository.findByIdCategory(id);
        return subCategoriesRepository.findAllByCategory(c);
    }

    @Override
    public Subcategory saveSubCategory(String name,ObjectId id ) {
        Category c=categoryRepository.findByIdCategory(id);
        System.out.println(c);
        Subcategory subcategory=new Subcategory(id,c,String.valueOf(subCategoriesRepository.countAllBy()),name);
        System.out.println(subcategory);
        return subCategoriesRepository.save(subcategory);
    }

    @Override
    public void deleteSubCategory(ObjectId id) {
        subCategoriesRepository.deleteById(id);
    }

    @Override
    public Subcategory update(Subcategory subcategory) {
        Subcategory su=subCategoriesRepository.findByIdSubCategory(new ObjectId(String.valueOf(subcategory.getIdSubCategory())));
        su.setSubCategoryName(subcategory.getSubCategoryName());
        return subCategoriesRepository.save(su);
    }
}
