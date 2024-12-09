package com.User_Management_Service.User_Management_System_Backend.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserRolesDTO {
    private String name;
    private String description;
    private Set<Long> permissionIds;
}

