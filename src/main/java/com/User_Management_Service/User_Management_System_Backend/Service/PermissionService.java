package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.Permission;
import com.User_Management_Service.User_Management_System_Backend.Exceptions.ResourceNotFoundException;
import com.User_Management_Service.User_Management_System_Backend.Repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission addPermission(Permission permission) {
        try{
            permissionRepository.save(permission);
            log.info("Permission added successfully");
        }

        catch (Exception e){
            log.error(e.getMessage());
        }

        return permission;
    }

    public List<Permission> viewAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission searchPermission(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }
}
