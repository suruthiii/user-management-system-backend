package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.RequestResponse;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public RequestResponse viewUserDetails() {
        RequestResponse response = new RequestResponse();

        try {
            List<Users> result = usersRepository.findAll();

            if (!result.isEmpty()) {
                response.setUsersList(result);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                response.setStatusCode(404);
                response.setMessage("No users found");
            }
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error occurred: " + e.getMessage());
        }

        return response;
    }

    public RequestResponse searchUser(Long id) {
        RequestResponse response = new RequestResponse();

        try {
            Users userById = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            response.setUsers(userById);
            response.setStatusCode(200);
            response.setMessage("User with id " + id + " found Successfully");

        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error occurred: " + e.getMessage());
        }
        return response;
    }

    public RequestResponse deleteUser(Long id) {
        RequestResponse response = new RequestResponse();

        try {
            Optional<Users> usersOptional = usersRepository.findById(id);

            if (usersOptional.isPresent()) {
                usersRepository.deleteById(id);
                response.setStatusCode(200);
                response.setMessage("User deleted Successfully");
            }
            else {
                response.setStatusCode(404);
                response.setMessage("User not found deletion");
            }
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error occurred: " + e.getMessage());
        }

        return response;
    }

    public RequestResponse updateUser(Long id, Users updateUser) {
        RequestResponse response = new RequestResponse();

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
                response.setUsers(savedUser);
                response.setStatusCode(200);
                response.setMessage("User updated Successfully");
            }
            else {
                response.setStatusCode(404);
                response.setMessage("User not found update");
            }
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error occurred: " + e.getMessage());
        }

        return response;
    }
}
