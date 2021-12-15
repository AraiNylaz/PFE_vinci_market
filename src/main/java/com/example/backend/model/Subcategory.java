package com.example.backend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("subcategory")
public class Subcategory {

    @Id
    private String idSubCategory;

    private String name;

    @NotNull(message = "You have to insert a value!")
    private ObjectId cateIdentification;

    private Category category;

    @NotNull(message = "You have to insert a value!")
    private String subCategoryName;

    public Subcategory(ObjectId cateIdentification,Category category, String name, String subCategoryName) {  //ObjectId idCate
        this.cateIdentification = cateIdentification;
        this.category=category;
        this.name=name;
        this.subCategoryName = subCategoryName;
    }
}
