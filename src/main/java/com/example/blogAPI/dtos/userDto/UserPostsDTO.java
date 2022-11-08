package com.example.blogAPI.dtos.userDto;

import com.example.blogAPI.models.Post;
import lombok.Data;

import java.util.Collection;

@Data
public class UserPostsDTO {
    private Long id;
    private Collection<Post> posts;

}
