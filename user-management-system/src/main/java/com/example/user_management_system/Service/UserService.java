package com.example.user_management_system.Service;

import com.example.user_management_system.Entity.User;
import com.example.user_management_system.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Search user by id
    public Optional<User> searchUser(int id) {
        return userRepository.findById(id);
    }

    // Update user by id
    public void updateUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow();

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }

        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }

        userRepository.save(existingUser);
    }

    // Delete user by id
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // View all users
    public List<User> viewUsers() {
        return userRepository.findAll();
    }
}
