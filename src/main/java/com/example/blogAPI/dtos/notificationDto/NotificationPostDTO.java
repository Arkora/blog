package com.example.blogAPI.dtos.notificationDto;

import com.example.blogAPI.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NotificationPostDTO {
    private String body;
    private User user;
    private Long postId;
}
