package com.example.blogAPI.repositories;

import com.example.blogAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);

    boolean existsByUsername(String username);
    User findByUsername(String username);

    @Query(nativeQuery = true,value = "select * from person p INNER join post ON post.user_id = p.id left join comment on comment.post_id = post.id order by post.created_at desc")
    Set<User> getRandomPost();

}
