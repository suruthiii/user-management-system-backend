package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.LoginDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.RequestResponse;
import com.User_Management_Service.User_Management_System_Backend.DTO.UsersDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Enums.UserStatus;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import com.User_Management_Service.User_Management_System_Backend.Repository.UsersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class AuthService {
    private final UsersRepository usersRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public Users register(@Valid UsersDTO registrationRequest) {
        Users newUser = new Users();

        // Populate user details
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setName(registrationRequest.getName());
        newUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        newUser.setGender(registrationRequest.getGender());
        newUser.setDateOfBirth(registrationRequest.getDateOfBirth());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Set default status as ACTIVE
        newUser.setStatus(UserStatus.ACTIVE);

        // Retrieve role by ID
        UserRoles role = userRoleRepository.findById(registrationRequest.getUserRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + registrationRequest.getUserRoleId()));

        newUser.setUserRole(role);

        try{
            newUser =  usersRepository.save(newUser);
        }

        catch (Exception e) {
            log.error(e.getMessage());
        }

        return newUser;
    }

    public RequestResponse login(@Valid LoginDTO loginRequest) {
        RequestResponse response = new RequestResponse();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Users user = usersRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            String jwt = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Logged in Successfully");
        }

        catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public RequestResponse refreshToken(RequestResponse refreshTokenRequest) {
        RequestResponse response = new RequestResponse();

        try {
            String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            Users user = usersRepository.findByEmail(email).orElseThrow();

            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                String jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getRefreshToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Token Refreshed Successfully");
            }
        }

        catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }
}
