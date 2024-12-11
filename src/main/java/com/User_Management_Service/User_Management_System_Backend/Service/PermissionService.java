package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permissions add(Permissions permission) {
        try{
            String error = "";
            if (permission.getPermission() == null || permission.getPermission().isEmpty()){
                error = "Permission cannot be null";
            }

            if (permission.getDescription() == null || permission.getDescription().isEmpty()){
                error = "Description cannot be null";
            }

            if (error.isEmpty()){
                permissionRepository.save(permission);
                log.info("Permission added successfully");
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
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
