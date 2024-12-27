package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.PermissionsDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permission Controller")
@RestController
@RequestMapping(path = "permissions/")
@RequiredArgsConstructor
@Validated
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    @Operation(
            description = "Add a new permission to the system",
            summary = "Add Permission",
            responses = {
                    @ApiResponse(
                            description = "Permission Successfully Created",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<Permissions> addPermission(@Valid @RequestBody PermissionsDTO permissionsDTO) {
        Permissions permission = new Permissions();
        permission.setPermission(permissionsDTO.getName());
        permission.setDescription(permissionsDTO.getDescription());
        return ResponseEntity.status(201).body(permissionService.addPermission(permission));
    }

    @GetMapping
    @Operation(
            description = "Retrieve all permissions for admin",
            summary = "Retrieve All Permissions",
            responses = {
                    @ApiResponse(
                            description = "Permissions Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public List<Permissions> viewAll() {
        return permissionService.viewAllPermissions();
    }

    @GetMapping("{id}")
    @Operation(
            description = "Search permission by id",
            summary = "Search Permissions",
            responses = {
                    @ApiResponse(
                            description = "Permission Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<Permissions> searchPermission(@PathVariable long id) {
        return ResponseEntity.ok(permissionService.searchPermission(id));
    }
}
