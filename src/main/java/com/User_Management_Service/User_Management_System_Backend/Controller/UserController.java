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
    public ResponseEntity<ReqRes> viewUserDetails() {
        return ResponseEntity.ok(userService.viewUserDetails());
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
    public ResponseEntity<ReqRes> searchUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.searchUser(id));
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
    public ResponseEntity<ReqRes> updateUser(@PathVariable long id, @RequestBody Users req) {
        return ResponseEntity.ok(userService.updateUser(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Delete User",
            summary = "Delete User",
            responses = {
                    @ApiResponse(
                            description = "Successful",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<ReqRes> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping()
    @Operation(
            description = "Register User",
            summary = "Register User",
            responses = {
                    @ApiResponse(description = "Successful", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403")
            })
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO reg) {
        return ResponseEntity.ok(authService.register(reg));
    }
}
