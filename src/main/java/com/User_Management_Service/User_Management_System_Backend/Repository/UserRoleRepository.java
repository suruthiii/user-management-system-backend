package com.User_Management_Service.User_Management_System_Backend.Repository;

import com.User_Management_Service.User_Management_System_Backend.Entity.User;
import com.User_Management_Service.User_Management_System_Backend.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> , JpaSpecificationExecutor<User> {
    @Query("SELECT ur.name FROM UserRole ur JOIN ur.permissions p WHERE p.name = :permissionName")
    List<String> findUserRolesWithPermission(String permissionName);
}
