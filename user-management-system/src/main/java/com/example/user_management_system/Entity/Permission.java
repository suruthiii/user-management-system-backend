package com.example.user_management_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String permission;
    private String description;
}
