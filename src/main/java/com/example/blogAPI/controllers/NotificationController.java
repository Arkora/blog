package com.example.blogAPI.controllers;

import com.example.blogAPI.dtos.response.MessageResponse;
import com.example.blogAPI.services.notification.NotificationService;
import com.example.blogAPI.services.notification.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/notification")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class NotificationController {
    @Autowired
    NotificationServiceImpl notificationService;

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().body(new MessageResponse("Notification deleted successfully"));
    }

    @GetMapping(value = "/{id}")
     public ResponseEntity<?> getNotification(@PathVariable Long id){
        return ResponseEntity.ok().body(notificationService.getAllNotifications(id));
    }



}
