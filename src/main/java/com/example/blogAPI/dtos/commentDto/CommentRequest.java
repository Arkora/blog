package com.example.blogAPI.dtos.commentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CommentRequest {
    private String body;
    private Long postId;
    private Long userId;
}
