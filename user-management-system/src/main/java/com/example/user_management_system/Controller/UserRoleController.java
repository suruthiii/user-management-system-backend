package com.example.user_management_system.Controller;

import com.example.user_management_system.Entity.User;
import com.example.user_management_system.Entity.UserRole;
import com.example.user_management_system.Service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@Tag(name = "User Role Controller")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    // View user roles
    @GetMapping
    @Operation(
            description = "View all user roles",
            summary = "View user roles",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public List<UserRole> viewUserRoles() { return userRoleService.viewUserRoles();}
}
