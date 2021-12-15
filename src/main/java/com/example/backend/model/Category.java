package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("categories")
public class Category {

    @Id
    private String idCategory;

    private String name;

    @NotNull(message = "You have to insert a value!")
    private String category;

    public Category(String idCategory,String name,String category) {
        this.idCategory = idCategory;
        this.name=name;
        this.category = category;
    }
}
