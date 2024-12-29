package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.UserRole;
import com.User_Management_Service.User_Management_System_Backend.Exceptions.ResourceNotFoundException;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public List<UserRole> viewAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public UserRole searchUserRole(Long id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User role not found"));
    }

    public UserRole updateUserRole(Long id, UserRole userRole) {
        UserRole existingUserRole = userRoleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User role not found"));

        try {
            if (userRole.getName() != null && !userRole.getName().isEmpty()){
                existingUserRole.setName(userRole.getName());
            }

            if (userRole.getDescription() != null && !userRole.getDescription().isEmpty()){
                existingUserRole.setDescription(userRole.getDescription());
            }

            userRoleRepository.save(existingUserRole);
            log.info("User role updated");
        }

        catch (Exception e){
            log.error(e.getMessage());
        }

        return existingUserRole;
    }

    public UserRole addUserRole(UserRole userRole) {
        try {
            userRoleRepository.save(userRole);
            log.info("User role added");
        }

        catch (Exception e){
            log.error(e.getMessage());
        }

        return userRole;
    }

    public List<String> getRoleByPermission(String permission){
        return userRoleRepository.findUserRolesWithPermission(permission);
    }
}
