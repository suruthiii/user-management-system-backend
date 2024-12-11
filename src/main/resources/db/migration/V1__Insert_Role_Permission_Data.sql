-- V1__Create_roles_and_permissions.sql

-- Create roles table
CREATE TABLE IF NOT EXISTS user_roles (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
    );

-- Create permissions table
CREATE TABLE IF NOT EXISTS permissions (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
    );

-- Create role_permission join table
CREATE TABLE IF NOT EXISTS role_permission (
                                               role_id BIGINT,
                                               permission_id BIGINT,
                                               PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES user_roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
    );

-- Insert initial roles
INSERT INTO user_roles (name, description) VALUES
                                               ('USER', 'Regular user role'),
                                               ('ADMIN', 'Administrator role');

-- Insert initial permissions
INSERT INTO permissions (name, description) VALUES
                                                ('READ', 'Read access to resources'),
                                                ('CREATE', 'Create access to resources'),
                                                ('UPDATE', 'Update access to resources'),
                                                ('DELETE', 'Delete access to resources');

-- Assign permissions to roles
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (1, 1), -- USER has READ
                                                         (2, 1), -- ADMIN has READ
                                                         (2, 2), -- ADMIN has CREATE
                                                         (2, 3), -- ADMIN has UPDATE
                                                         (2, 4); -- ADMIN has DELETE

