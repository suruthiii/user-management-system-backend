package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.LoginDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.RequestResponse;
import com.User_Management_Service.User_Management_System_Backend.DTO.UsersDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Controller")
@RestController
@RequestMapping(path = "auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
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
    public ResponseEntity<Users> register(@RequestBody UsersDTO reg) {
        return ResponseEntity.ok(authService.register(reg));
    }

    @PostMapping("/login")
    @Operation(
            description = "Login to the system as a user",
            summary = "User Login",
            responses = {
                    @ApiResponse(
                            description = "User Logged In Successfully",
                            responseCode = "200"),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> login(@RequestBody LoginDTO req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    @Operation(
            description = "Refresh token",
            summary = "Token Refresh",
            responses = {
                    @ApiResponse(
                            description = "Token Successfully Refreshed",
                            responseCode = "200"),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403")
            })
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }
}
