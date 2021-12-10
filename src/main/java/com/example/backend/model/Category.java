package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("categories")
public class Category {

    @Id
    private String idCategory;

    @Indexed(unique = true)
    @NotNull(message = "You have to insert a value!")
    private String name;

    public Category(String idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }
}
