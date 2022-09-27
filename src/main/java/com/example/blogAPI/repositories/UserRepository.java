package com.example.blogAPI.repositories;

import com.example.blogAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
