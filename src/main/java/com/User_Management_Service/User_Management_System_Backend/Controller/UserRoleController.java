package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.UserRoleDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Permission;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRole;
import com.User_Management_Service.User_Management_System_Backend.Service.PermissionService;
import com.User_Management_Service.User_Management_System_Backend.Service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "User Role Controller")
@RestController
@RequestMapping(path = "userRoles/")
@RequiredArgsConstructor
@Validated
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final PermissionService permissionService;

    @GetMapping
    @Operation(
            description = "Retrieve all user roles",
            summary = "Retrieve All User Roles",
            responses = {
                    @ApiResponse(
                            description = "User Roles Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public List<UserRole> viewAllUserRoles() {
        return userRoleService.viewAllUserRoles();
    }

    @GetMapping("{id}")
    @Operation(
            description = "Search user role by id",
            summary = "Search User Roles",
            responses = {
                    @ApiResponse(
                            description = "User Role Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<UserRole> searchUserRole(@PathVariable long id) {
        return ResponseEntity.ok(userRoleService.searchUserRole(id));
    }

    @PutMapping("{id}")
    @Operation(
            description = "Update user role by id",
            summary = "Update User Roles",
            responses = {
                    @ApiResponse(
                            description = "User Role Successfully Updated",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<UserRole> updateUserRole(@PathVariable long id, @RequestBody UserRoleDTO userRoleDTO) {
        return ResponseEntity.ok(userRoleService.updateUserRole(id, userRoleDTO));
    }

    @PostMapping
    @Operation(
            description = "Add user role",
            summary = "Add User Role",
            responses = {
                    @ApiResponse(
                            description = "User Role Successfully Created",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<UserRole> addUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setName(userRoleDTO.getName());
        userRole.setDescription(userRoleDTO.getDescription());

        // Map permission IDs to Permission entities
        Set<Permission> permissions = userRoleDTO.getPermissionIds().stream()
                .map(permissionService::searchPermission)
                .collect(Collectors.toSet());
        userRole.setPermissions(permissions);

        return ResponseEntity.status(201).body(userRoleService.addUserRole(userRole));
    }
}
