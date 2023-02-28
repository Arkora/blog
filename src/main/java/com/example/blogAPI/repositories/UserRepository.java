package com.example.blogAPI.repositories;

import com.example.blogAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
@EnableJpaRepositories
public interface UserRepository  extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);

    boolean existsByUsername(String username);
    User findByUsername(String username);



}
