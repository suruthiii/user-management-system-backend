package com.User_Management_Service.User_Management_System_Backend.Controller;

import com.User_Management_Service.User_Management_System_Backend.DTO.LoginDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.RequestResponse;
import com.User_Management_Service.User_Management_System_Backend.DTO.UserDTO;
import com.User_Management_Service.User_Management_System_Backend.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Controller")
@RestController
@RequestMapping(path = "auth/")
@RequiredArgsConstructor
@Validated
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
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO request) {
        return authService.register(request);
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
    public ResponseEntity<RequestResponse> login(@Valid @RequestBody LoginDTO request) {
        return authService.login(request);
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
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
        return authService.refreshToken(request);
    }
}
