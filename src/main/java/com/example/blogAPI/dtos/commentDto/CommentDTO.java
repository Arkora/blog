package com.example.blogAPI.dtos.commentDto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private String body;
    private String firstname;
    private String lastname;
    private Date createdAt;
}
