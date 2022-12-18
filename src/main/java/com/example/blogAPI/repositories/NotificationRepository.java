package com.example.blogAPI.repositories;

import com.example.blogAPI.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
