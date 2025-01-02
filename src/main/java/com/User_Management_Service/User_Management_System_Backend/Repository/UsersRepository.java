package com.User_Management_Service.User_Management_System_Backend.Repository;

import com.User_Management_Service.User_Management_System_Backend.Entity.User;
import java.util.Optional;

public interface UsersRepository extends CoreRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
