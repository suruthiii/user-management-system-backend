package com.User_Management_Service.User_Management_System_Backend.Repository;

import com.User_Management_Service.User_Management_System_Backend.Entity.Permission;
import com.User_Management_Service.User_Management_System_Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<Permission, Long> , JpaSpecificationExecutor<User> {

}
