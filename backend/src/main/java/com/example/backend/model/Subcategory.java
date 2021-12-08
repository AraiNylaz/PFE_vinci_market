package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("subCategories")
public class Subcategory {

    @Id
    private String idSubCategory;

    private Category category;
    private String name;

    public Subcategory(String idSubCategory, Category category, String name) {
        this.idSubCategory = idSubCategory;
        this.category = category;
        this.name = name;
    }
}
