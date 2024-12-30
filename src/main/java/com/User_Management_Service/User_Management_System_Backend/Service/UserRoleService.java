package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.UserRoleDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Permission;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRole;
import com.User_Management_Service.User_Management_System_Backend.Exceptions.ResourceNotFoundException;
import com.User_Management_Service.User_Management_System_Backend.Repository.PermissionRepository;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final PermissionRepository permissionRepository;

    public List<UserRole> viewAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public UserRole searchUserRole(Long id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User role not found"));
    }

    public UserRole updateUserRole(Long id, UserRoleDTO userRole) {
        UserRole existingUserRole = userRoleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User role not found"));

        try {
            if (userRole.getName() != null && !userRole.getName().isEmpty()){
                existingUserRole.setName(userRole.getName());
            }

            if (userRole.getDescription() != null && !userRole.getDescription().isEmpty()){
                existingUserRole.setDescription(userRole.getDescription());
            }

            if (userRole.getPermissionIds() != null){
                Set<Permission> permissions = new HashSet<>();
                for (Long permissionId : userRole.getPermissionIds()) {
                    Permission permission = permissionRepository.findById(permissionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Permission not found for ID: " + permissionId));
                    permissions.add(permission);
                }
                existingUserRole.setPermissions(permissions);
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
