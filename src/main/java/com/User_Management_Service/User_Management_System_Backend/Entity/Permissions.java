package com.User_Management_Service.User_Management_System_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "permissions")
@Data
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String permission;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoles> roles = new HashSet<>();
}
