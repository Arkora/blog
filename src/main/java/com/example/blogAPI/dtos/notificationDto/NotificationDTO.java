package com.example.blogAPI.dtos.notificationDto;

import com.example.blogAPI.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private String body;
    private Long userId;
    private Long postId;
}
