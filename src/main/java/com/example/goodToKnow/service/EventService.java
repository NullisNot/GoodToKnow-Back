package com.example.goodToKnow.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.repository.EventRepository;

@Service
public class EventService {

  @Autowired
  EventRepository eventRepository;

  public List<Event> getEventsByTimeInterval(LocalDateTime startOfDay, LocalDateTime endOfDay) {
    return eventRepository.findByStartsAtBetween(startOfDay, endOfDay);
  }

}
