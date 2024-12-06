package com.example.user_management_system.Service;

import com.example.user_management_system.Entity.User;
import com.example.user_management_system.Entity.UserRole;
import com.example.user_management_system.Repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    // View all user roles
    public List<UserRole> viewUserRoles() {
        return userRoleRepository.findAll();
    }
}
