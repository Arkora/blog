package com.example.blogAPI.dtos.userDto;

import com.example.blogAPI.dtos.postDto.PostDTO;
import lombok.Data;

import java.util.Collection;

@Data
public class UserPostsDTO {
    private Collection<PostDTO> posts;


}
