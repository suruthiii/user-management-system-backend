package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public List<UserRoles> viewAll() {
        return userRoleRepository.findAll();
    }

    public UserRoles searchUserRole(Long id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("User role not found"));
    }

    public UserRoles updateUserRole(Long id, UserRoles userRole) {
        UserRoles existingUserRole = userRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("User role not found"));

        if (userRole.getName() != null && !userRole.getName().isEmpty()){
            existingUserRole.setName(userRole.getName());
        }

        if (userRole.getDescription() != null && !userRole.getDescription().isEmpty()){
            existingUserRole.setDescription(userRole.getDescription());
        }

        userRoleRepository.save(existingUserRole);
        return existingUserRole;
    }

    public UserRoles addUserRole(UserRoles userRole) {
        String error = "";
        if (userRole.getName() == null || userRole.getName().isEmpty()){
            error = "Name cannot be empty";
        }

        if (userRole.getDescription() == null || userRole.getDescription().isEmpty()){
            error = "Description cannot be empty";
        }

        if (error.isEmpty()){
            userRoleRepository.save(userRole);
        }
        return userRole;
    }

    public List<String> getRoleByPermission(String permission){
        List<String> userRoles = userRoleRepository.findUserRolesWithPermission(permission);

        return userRoles;
    }
}
