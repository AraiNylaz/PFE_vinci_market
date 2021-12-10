package com.example.backend.model;

import com.example.backend.Enums.Campus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("users")
public class User {

    @Id
    private String idUser;

    @NotNull(message = "You have to insert a value!")
    private String lastName;

    @NotNull(message = "You have to insert a value!")
    private String firstName;

    @NotNull(message = "You have to insert a value!")
    private String password;

    @NotNull(message = "You have to insert a value!")
    private Campus campus;

    private String campusName;

    @NotNull(message = "You have to insert a value!")
    private String phone;

    @Indexed(unique = true)
    @NotNull(message = "You have to insert a value!")
    private String mail;

    private boolean isAdmin;

    private boolean isBan;

    public User(String idUser, String lastName, String firstName, String password, Campus campus,String campusName, String phone, String mail, boolean isAdmin,boolean isBan) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.campus = campus;
        this.campusName=campusName;
        this.phone = phone;
        this.mail = mail;
        this.isAdmin = isAdmin;
        this.isBan=isBan;
    }
}
