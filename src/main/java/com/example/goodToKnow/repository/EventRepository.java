package com.example.goodToKnow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByStartsAtBetween(LocalDateTime start, LocalDateTime end);
}
