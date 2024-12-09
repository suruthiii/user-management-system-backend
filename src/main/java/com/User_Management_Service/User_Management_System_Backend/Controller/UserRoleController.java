package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.UserRolesDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Service.PermissionService;
import com.User_Management_Service.User_Management_System_Backend.Service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "User Role Controller")
@RestController
@RequestMapping(path = "/userRoles")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final PermissionService permissionService;

    @GetMapping
    @Operation(
            description = "Get All Users for Admin",
            summary = "Get All Users",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<UserRoles> viewAllUserRoles() {
        return userRoleService.viewAll();
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get User",
            summary = "Get Users",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<UserRoles> searchUserRole(@PathVariable long id) {
        return ResponseEntity.ok(userRoleService.searchUserRole(id));
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Update User",
            summary = "Update User",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<UserRoles> updateUserRole(@PathVariable long id, @RequestBody UserRolesDTO userRolesDTO) {
        UserRoles userRole = new UserRoles();
        userRole.setName(userRolesDTO.getName());
        userRole.setDescription(userRolesDTO.getDescription());

        // Map permission IDs to Permission entities
        Set<Permissions> permissions = userRolesDTO.getPermissionIds().stream()
                .map(permissionService::searchUser)
                .collect(Collectors.toSet());
        userRole.setPermissions(permissions);

        return ResponseEntity.ok(userRoleService.updateUserRole(id, userRole));
    }

    @PostMapping
    @Operation(
            description = "Update User",
            summary = "Update User",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<UserRoles> addUserRole(@RequestBody UserRolesDTO userRolesDTO) {
        UserRoles userRole = new UserRoles();
        userRole.setName(userRolesDTO.getName());
        userRole.setDescription(userRolesDTO.getDescription());

        // Map permission IDs to Permission entities
        Set<Permissions> permissions = userRolesDTO.getPermissionIds().stream()
                .map(permissionService::searchUser)
                .collect(Collectors.toSet());
        userRole.setPermissions(permissions);

        return ResponseEntity.status(201).body(userRoleService.addUserRole(userRole));
    }
}
