package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.UserDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.UserResponseDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.User;
import com.User_Management_Service.User_Management_System_Backend.Service.AuthService;
import com.User_Management_Service.User_Management_System_Backend.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller")
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
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/view/{userId}")
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
    public ResponseEntity<UserResponseDTO> searchUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.searchUser(userId));
    }

    @PutMapping("/update/{userId}")
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
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/delete/{userId}")
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
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/create")
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
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {
        return authService.register(request);
    }
}
