package com.User_Management_Service.User_Management_System_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CoreRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}

