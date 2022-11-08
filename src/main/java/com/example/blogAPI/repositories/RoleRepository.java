package com.example.blogAPI.repositories;

import com.example.blogAPI.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String username);
}
