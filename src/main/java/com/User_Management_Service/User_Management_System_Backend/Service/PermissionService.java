package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permissions add(Permissions permission) {
        String error = "";
        if (permission.getPermission() == null || permission.getPermission().isEmpty()){
            error = "Permission cannot be null";
        }

        if (permission.getDescription() == null || permission.getDescription().isEmpty()){
            error = "Description cannot be null";
        }

        if (error.isEmpty()){
            permissionRepository.save(permission);
        }
        return permission;
    }

    public List<Permissions> viewAll() {
        return permissionRepository.findAll();
    }

    public Permissions searchUser(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
    }
}
