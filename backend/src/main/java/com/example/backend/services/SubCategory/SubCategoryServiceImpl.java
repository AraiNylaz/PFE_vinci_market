package com.example.backend.services.SubCategory;

import com.example.backend.model.Subcategory;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.SubCategoriesRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    private final SubCategoriesRepository subCategoriesRepository;
    private final CategoryRepository categoryRepository;


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
        return subCategoriesRepository.findAllByIdCategory(id);
    }

    @Override
    public Subcategory saveSubCategory(Subcategory subcategory) {
        if(categoryRepository.findByIdCategory(new ObjectId(String.valueOf(subcategory.getIdCategory())))!=null){
            return subCategoriesRepository.save(subcategory);
        }
        return null;
    }

    @Override
    public void deleteSubCategory(ObjectId id) {
        subCategoriesRepository.deleteByIdSubCategory(id);
    }

    @Override
    public Subcategory update(Subcategory subcategory) {
        Subcategory su=subCategoriesRepository.findByIdSubCategory(new ObjectId(String.valueOf(subcategory.getIdSubCategory())));
        su.setName(subcategory.getName());
        su.setIdCategory(su.getIdCategory());
        return subCategoriesRepository.save(su);
    }
}
