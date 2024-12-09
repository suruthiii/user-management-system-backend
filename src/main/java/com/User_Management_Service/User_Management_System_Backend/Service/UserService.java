package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.LoginDTO;
import com.User_Management_Service.User_Management_System_Backend.DTO.ReqRes;
import com.User_Management_Service.User_Management_System_Backend.DTO.RegisterDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Repository.UserRoleRepository;
import com.User_Management_Service.User_Management_System_Backend.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public ReqRes viewUserDetails() {
        ReqRes resp = new ReqRes();
        try {
            List<Users> result = usersRepository.findAll();
            if (!result.isEmpty()) {
                resp.setUsersList(result);
                resp.setStatusCode(200);
                resp.setMessage("Successful");
            } else {
                resp.setStatusCode(404);
                resp.setMessage("No users found");
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("Error occurred: " + e.getMessage());
        }
        return resp;
    }

    public ReqRes searchUser(Long id) {
        ReqRes resp = new ReqRes();
        try {
            Users userById = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            resp.setUsers(userById);
            resp.setStatusCode(200);
            resp.setMessage("User with id " + id + " found Successfully");

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("Error occurred: " + e.getMessage());
        }
        return resp;
    }

    public ReqRes deleteUser(Long id) {
        ReqRes resp = new ReqRes();
        try {
            Optional<Users> usersOptional = usersRepository.findById(id);
            if (usersOptional.isPresent()) {
                usersRepository.deleteById(id);
                resp.setStatusCode(200);
                resp.setMessage("User deleted Successfully");
            } else {
                resp.setStatusCode(404);
                resp.setMessage("User not found deletion");
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("Error occurred: " + e.getMessage());
        }
        return resp;
    }

    public ReqRes updateUser(Long id, Users updateUser) {
        ReqRes resp = new ReqRes();
        try {
            Optional<Users> usersOptional = usersRepository.findById(id);
            if (usersOptional.isPresent()) {
                Users existingUser = usersOptional.get();
                existingUser.setEmail(updateUser.getEmail());
                existingUser.setName(updateUser.getName());

                if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                }
                Users savedUser = usersRepository.save(existingUser);
                resp.setUsers(savedUser);
                resp.setStatusCode(200);
                resp.setMessage("User updated Successfully");
            } else {
                resp.setStatusCode(404);
                resp.setMessage("User not found update");
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("Error occurred: " + e.getMessage());
        }
        return resp;
    }
}
