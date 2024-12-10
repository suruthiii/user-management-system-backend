package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.ReqRes;
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
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping()
    @Operation(
            description = "Retrieve all users for admin",
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
    public ResponseEntity<ReqRes> viewUserDetails() {
        return ResponseEntity.ok(userService.viewUserDetails());
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<ReqRes> searchUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.searchUser(id));
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<ReqRes> updateUser(@PathVariable long id, @RequestBody Users req) {
        return ResponseEntity.ok(userService.updateUser(id, req));
    }

    @DeleteMapping("/{id}")
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
    public ResponseEntity<ReqRes> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
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
