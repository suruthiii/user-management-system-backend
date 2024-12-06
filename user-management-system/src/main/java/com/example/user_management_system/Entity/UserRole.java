package com.example.user_management_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_role")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    private String description;
}
