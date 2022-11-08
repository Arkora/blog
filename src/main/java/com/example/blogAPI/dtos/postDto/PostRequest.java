package com.example.blogAPI.dtos.postDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostRequest {
    private String title;
    private String body;
    private Long userId;
}
