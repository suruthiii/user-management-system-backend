package com.User_Management_Service.User_Management_System_Backend.Repository;

import com.User_Management_Service.User_Management_System_Backend.Entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    @Query("SELECT ur.name FROM UserRoles ur JOIN ur.permissions p WHERE p.permission = :permissionName")
    List<String> findUserRolesWithPermission(String permissionName);
}
