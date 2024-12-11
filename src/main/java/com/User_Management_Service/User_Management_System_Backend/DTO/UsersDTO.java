package com.User_Management_Service.User_Management_System_Backend.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private LocalDate dateOfBirth;
    private Long userRoleId; // Role ID to associate the user with
}

