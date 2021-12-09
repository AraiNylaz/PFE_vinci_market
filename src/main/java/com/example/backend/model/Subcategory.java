package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("subCategories")
public class Subcategory {

    @Id
    private String idSubCategory;

    private ObjectId idCategory;
    private Category category;
    private String name;

    public Subcategory(String idSubCategory,ObjectId idCategory, Category category, String name) {
        this.idSubCategory = idSubCategory;
        this.idCategory=idCategory;
        this.category = category;
        this.name = name;
    }
}
