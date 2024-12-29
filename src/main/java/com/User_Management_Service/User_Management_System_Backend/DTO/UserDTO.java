package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.User_Management_Service.User_Management_System_Backend.Validations.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    @ValidPhoneNumber
    private String phoneNumber;

    @ValidGender
    private String gender;

    @ValidBirthday
    private LocalDate dateOfBirth;

    @NotNull(message = "Role ID cannot be null")
    private Long userRoleId;
}

