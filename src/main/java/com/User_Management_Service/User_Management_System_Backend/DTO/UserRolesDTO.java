package com.User_Management_Service.User_Management_System_Backend.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UserRolesDTO {
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;


    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    private String description;

    @NotEmpty(message = "Permission ID cannot be empty")
    @NotNull(message = "Permission ID cannot be null")
    private Set<Long> permissionIds;
}

