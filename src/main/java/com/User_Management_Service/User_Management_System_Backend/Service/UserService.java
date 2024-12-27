package com.User_Management_Service.User_Management_System_Backend.Service;

import com.User_Management_Service.User_Management_System_Backend.DTO.UserResponseDTO;
import com.User_Management_Service.User_Management_System_Backend.Entity.Users;
import com.User_Management_Service.User_Management_System_Backend.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        try {
            List<Users> result = usersRepository.findAll();
            return result.stream()
                    .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getGender(), user.getDateOfBirth(), user.getStatus(), user.getUserRole()))
                    .collect(Collectors.toList());
        }

        catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public UserResponseDTO searchUser(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getGender(), user.getDateOfBirth(), user.getStatus(), user.getUserRole());
    }

    public UserResponseDTO deleteUser(Long id) {
        try {
            Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            usersRepository.deleteById(id);
            return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getGender(), user.getDateOfBirth(), user.getStatus(), user.getUserRole());
        }

        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public Users updateUser(Long id, Users updateUser) {
        try {
            Users existingUser = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

            if (updateUser.getDateOfBirth() != null) {
                existingUser.setDateOfBirth(updateUser.getDateOfBirth());
            }

            if (updateUser.getName() != null && !updateUser.getName().isEmpty()) {
                existingUser.setName(updateUser.getName());
            }

            if (updateUser.getPhoneNumber() != null && !updateUser.getPhoneNumber().isEmpty()) {
                existingUser.setPhoneNumber(updateUser.getPhoneNumber());
            }

            if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            }

            return usersRepository.save(existingUser);
        }

        catch (Exception e) {
            log.error(e.getMessage());
            return updateUser;
        }
    }
}
