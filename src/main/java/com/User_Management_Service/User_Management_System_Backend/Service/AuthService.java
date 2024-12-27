package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.*;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Enums.UserStatus;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import com.User_Management_Service.User_Management_System_Backend.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UsersRepository usersRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public ResponseEntity<RegistrationResponseDTO> register(UsersDTO registrationRequest) {
        Users newUser = new Users();

        if (usersRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body(new RegistrationResponseDTO("Email", "Email is already in use"));
        }

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
            usersRepository.save(newUser);
            return ResponseEntity.status(200).body(new RegistrationResponseDTO("No Error Found", "User Successfully added"));
        }

        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(new RegistrationResponseDTO("Error Found", "Can't add user"));
        }
    }


    public ResponseEntity<RequestResponse> login(LoginDTO loginRequest) {
        RequestResponse response = new RequestResponse();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Users user = usersRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            String jwt = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(user);

            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Logged in Successfully");

            return ResponseEntity.status(200).body(response);
        }

        catch (Exception e) {
            response.setError(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    public ResponseEntity<RequestResponse> refreshToken(RequestResponse request) {
        RequestResponse response = new RequestResponse();

        try {
            String email = jwtUtils.extractUsername(request.getToken());
            Users user = usersRepository.findByEmail(email).orElseThrow();

            if (jwtUtils.isTokenValid(request.getToken(), user)) {
                String jwt = jwtUtils.generateToken(user);
                String refreshToken = jwtUtils.generateRefreshToken(user);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24Hrs");
                response.setMessage("Token Refreshed Successfully");

                return ResponseEntity.status(200).body(response);
            }

            return ResponseEntity.status(403).body(response);
        }

        catch (Exception e) {
            response.setError(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
