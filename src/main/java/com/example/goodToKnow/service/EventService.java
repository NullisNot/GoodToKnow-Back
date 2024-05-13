package com.example.goodToKnow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;
import com.example.goodToKnow.repository.EventRepository;

@Service
public class EventService {

  @Autowired
  EventRepository eventRepository;

  public List<Event> getEventsByTimeInterval(LocalDateTime startOfDay, LocalDateTime endOfDay) {
    return eventRepository.findByStartsAtBetween(startOfDay, endOfDay);
  }

  public EventOut saveEvent(EventIn eventIn) {
    Event event = eventRepository.save(
        new Event(eventIn.getSubject(), eventIn.getTeacher(), eventIn.getStartsAt(), eventIn.getFinishesAt(),
            eventIn.getBuilding(), eventIn.getClassroom(), eventIn.getLink(), eventIn.getComments()));

    EventOut eventOut = new EventOut(event.getSubject(), event.getTeacher(), event.getStartsAt(), event.getFinishesAt(),
        event.getBuilding(), event.getClassroom(), event.getLink(), event.getComments());

    return eventOut;
  }

  public void delete(Long id) {
    eventRepository.deleteById(id);
  }

  public Event editEvent(Event event) {
    Optional<Event> eventToEdit = eventRepository.findById(event.getId());
    if (Optional.empty().equals(eventToEdit)) {
      return null;
    }
    return eventRepository.save(event);
  }
}
