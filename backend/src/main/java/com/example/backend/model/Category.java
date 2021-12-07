package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("categories")
public class Category {

    @Id
    private int idCategory;
    private String name;

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }
}
