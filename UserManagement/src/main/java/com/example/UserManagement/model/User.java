package com.example.UserManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private int userId;
    private String userName;
    private String time;
    private String eMail;
    private String number;
    private String dob;

}
