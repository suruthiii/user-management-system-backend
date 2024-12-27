package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private LocalDate dateOfBirth;
    private UserStatus status;
    private UserRoles userRole;
}
