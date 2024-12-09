package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.User_Management_Service.User_Management_System_Backend.Enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private LocalDate dateOfBirth;
}
