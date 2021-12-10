package com.example.backend.model;

import com.example.backend.Enums.Campus;
import lombok.Data;

@Data
public class UserDTO {

    private String idUser;
    private String lastName;
    private String firstName;
    private String campus;
    private String phone;
    private String mail;
    private boolean isAdmin;

    public UserDTO(String idUser, String lastName, String firstName, String campus, String phone, String mail, boolean isAdmin) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.campus = campus;
        this.phone = phone;
        this.mail = mail;
        this.isAdmin = isAdmin;
    }
}
