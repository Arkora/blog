package com.example.blogAPI.repositories;

import com.example.blogAPI.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    boolean existsById(Long id);
}
