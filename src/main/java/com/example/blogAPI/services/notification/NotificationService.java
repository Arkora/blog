package com.example.blogAPI.services.notification;

import com.example.blogAPI.dtos.notificationDto.NotificationDTO;
import com.example.blogAPI.dtos.notificationDto.NotificationPostDTO;

import java.util.Collection;

public interface NotificationService {
    void save(NotificationPostDTO notificationPostDTO);
    void deleteNotification(Long id);
    Collection<NotificationDTO> getAllNotifications(Long id);
//    void getAllNotifications(Long id);


}
