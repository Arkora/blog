package com.example.blogAPI.services.notification;

import com.example.blogAPI.dtos.notificationDto.NotificationDTO;
import com.example.blogAPI.dtos.notificationDto.NotificationPostDTO;
import com.example.blogAPI.models.Notification;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.NotificationRepository;
import com.example.blogAPI.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    private NotificationDTO convertEntityToNotificationDto(Notification notification){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
       NotificationDTO notificationDTO = modelMapper.map(notification,NotificationDTO.class);
        return notificationDTO;
    }
    @Override
    public void save(NotificationPostDTO notificationPostDTO) {
        Notification notification = new Notification(notificationPostDTO.getBody(),notificationPostDTO.getUser(),notificationPostDTO.getPostId());
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not exists"));
        notificationRepository.delete(notification);
    }

    @Override
    public Collection<NotificationDTO> getAllNotifications(Long id) {
      Collection<NotificationDTO> notificationDTOS = new ArrayList();
      User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not exists"));
      user.getNotifications().forEach((notification) ->
              notificationDTOS.add(convertEntityToNotificationDto(notification))
              //System.out.println(notification)
              );

        return notificationDTOS;
    }

}
