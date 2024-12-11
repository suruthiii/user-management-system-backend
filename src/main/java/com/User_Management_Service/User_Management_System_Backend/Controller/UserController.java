package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.RequestResponse;
import com.User_Management_Service.User_Management_System_Backend.DTO.UsersDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Service.AuthService;
import com.User_Management_Service.User_Management_System_Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Management Controller")
@RestController
@RequestMapping(path = "users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/view")
    @Operation(
            description = "Retrieve all users",
            summary = "Retrieve All Users",
            responses = {
                    @ApiResponse(
                            description = "Users Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> viewUserDetails() {
        return ResponseEntity.ok(userService.viewUserDetails());
    }

    @GetMapping("/{userId}")
    @Operation(
            description = "Search user by id",
            summary = "Search Users",
            responses = {
                    @ApiResponse(
                            description = "User Successfully Retrieved",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> searchUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.searchUser(userId));
    }

    @PutMapping("/{userId}")
    @Operation(
            description = "Update user by id",
            summary = "Update User",
            responses = {
                    @ApiResponse(
                            description = "User Successfully Updated",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> updateUser(@PathVariable long userId, @RequestBody Users req) {
        return ResponseEntity.ok(userService.updateUser(userId, req));
    }

    @DeleteMapping("/{userId}")
    @Operation(
            description = "Delete user by id",
            summary = "Delete User",
            responses = {
                    @ApiResponse(
                            description = "User Successfully Deleted",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> deleteUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PostMapping()
    @Operation(
            description = "Register a new user to the system",
            summary = "Register User",
            responses = {
                    @ApiResponse(
                            description = "User Successfully Created",
                            responseCode = "201"),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO reg) {
        return ResponseEntity.ok(authService.register(reg));
    }
}
