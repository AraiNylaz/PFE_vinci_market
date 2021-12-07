package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("subCategories")
public class Subcategory {

    @Id
    private int idSubCategory;

    private int idCategory;
    private String name;

    public Subcategory(int idSubCategory, int idCategory, String name) {
        this.idSubCategory = idSubCategory;
        this.idCategory = idCategory;
        this.name = name;
    }
}
