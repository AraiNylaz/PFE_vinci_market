package com.example.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
public class User {

    @Id
    private String idUser;
    private String lastName;
    private String firstName;
    private String password;
    private String campus;
    private String phone;
    private String mail;
    private boolean isAdmin;

    public User(String idUser, String lastName, String firstName, String password, String campus, String phone, String mail, boolean isAdmin) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.campus = campus;
        this.phone = phone;
        this.mail = mail;
        this.isAdmin = isAdmin;
    }
}
