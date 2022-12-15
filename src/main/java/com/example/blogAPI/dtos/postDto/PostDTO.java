package com.example.blogAPI.dtos.postDto;

import com.example.blogAPI.dtos.commentDto.CommentDTO;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class PostDTO {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String title;
    private String body;
    private Date createdAt;
    private Collection<CommentDTO> comments;
}
