package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.PermissionsDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.RegisterDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.ReqRes;
import com.User_Management_Service.User_Management_System_Backend.Entity.Permissions;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permission Controller")
@RestController
@RequestMapping(path = "/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    @Operation(
            description = "Register User",
            summary = "Register User",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<Permissions> addPermission(@RequestBody PermissionsDTO permissionsDTO) {
        Permissions permission = new Permissions();
        permission.setPermission(permissionsDTO.getName());
        permission.setDescription(permissionsDTO.getDescription());
        return ResponseEntity.status(201).body(permissionService.add(permission));
    }

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
    public List<Permissions> viewAll() {
        return permissionService.viewAll();
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
    public ResponseEntity searchPermission(@PathVariable long id) {
        return ResponseEntity.ok(permissionService.searchUser(id));
    }
}
