package com.example.goodToKnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Notification;
import com.example.goodToKnow.repository.NotificationRepository;

@Service
public class NotificationService {

  @Autowired
  NotificationRepository notificationRepository;

  public Notification saveNotification(Notification notification) {
    notificationRepository.save(notification);

    return notification;
  }

  public List<Notification> getNotifications() {
    List<Notification> notifications = notificationRepository.findAll();

    return notifications;
  }

}
