package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
