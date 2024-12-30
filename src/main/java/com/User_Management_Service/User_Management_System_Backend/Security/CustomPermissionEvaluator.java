package com.User_Management_Service.User_Management_System_Backend.Security;

import com.User_Management_Service.User_Management_System_Backend.Service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private final UserRoleService userRoleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || permission == null) {
            return false;
        }

        // Get the user's roles
        List<String> userRoles = authentication.getAuthorities().stream()
                .map(Object::toString)
                .toList();

        // Check if any of the user's roles have the required permission
        List<String> rolesWithPermission = userRoleService.getRoleByPermission(permission.toString());

        // If no roles are found with the permission, default to ADMIN
        if (rolesWithPermission.isEmpty()) {
            rolesWithPermission = List.of("ADMIN");
        }

        return userRoles.stream().anyMatch(rolesWithPermission::contains);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, null, permission);
    }
}