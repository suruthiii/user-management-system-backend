package com.example.user_management_system.Controller;

import com.example.user_management_system.Entity.User;
import com.example.user_management_system.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@Tag(name = "User Controller")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create new user
    @PostMapping
    @Operation(
            description = "Create new user",
            summary = "Add new user to the system",
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

    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    // Search user
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search user by id",
            summary = "Search user by using the id",
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

    public Optional<User> searchUser(@PathVariable("id") int id) {
        return userService.searchUser(id);
    }

    // View all user details
    @GetMapping
    @Operation(
            description = "View all user details",
            summary = "View user details",
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

    public List<User> viewUsers() { return userService.viewUsers();}

    // Update user
    @PutMapping (path = "{id}")
    @Operation(
            description = "Update user by id",
            summary = "Update user by using the id",
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

    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    // Delete user
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete user by id",
            summary = "Delete user by using the id",
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

    public void deleteUser(@PathVariable("id") int id) { userService.deleteUser(id); }
}
