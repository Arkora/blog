package com.example.blogAPI.repositories;

import com.example.blogAPI.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post, Long> {
    Collection<Post> findAllByOrderByCreatedAtDesc();

    @Query(value = "SELECT p from Post p where p.user.id = :id order by p.createdAt desc  ")
    Collection<Post> findAllByUserIdAndSortByDate(@Param("id") Long id);
    boolean existsById(Long id);

}
