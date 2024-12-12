package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Repository.PermissionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permissions addPermission(@Valid Permissions permission) {
        try{
            permissionRepository.save(permission);
            log.info("Permission added successfully");
        }

        catch (Exception e){
            log.error(e.getMessage());
        }

        return permission;
    }

    public List<Permissions> viewAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permissions searchPermission(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
    }
}
