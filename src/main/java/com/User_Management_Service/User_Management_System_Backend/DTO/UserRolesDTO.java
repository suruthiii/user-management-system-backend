package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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

