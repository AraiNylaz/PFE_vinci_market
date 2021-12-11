package com.example.backend.model;

import com.example.backend.Enums.Campus;
import lombok.Data;

@Data
public class UserDTO {

    private String idUser;
    private String lastName;
    private String firstName;
    private Campus campus;
    private String campusName;
    private String phone;
    private String mail;
    private boolean isAdmin;
    private boolean isBan;

    public UserDTO(String idUser, String lastName, String firstName, Campus campus,String campusName, String phone, String mail, boolean isAdmin,boolean isBan) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.campus = campus;
        this.campusName=campusName;
        this.phone = phone;
        this.mail = mail;
        this.isAdmin = isAdmin;
        this.isBan=isBan;
    }
}
