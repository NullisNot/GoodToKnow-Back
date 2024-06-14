package com.example.goodToKnow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.errors.EventNotFoundException;
import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;
import com.example.goodToKnow.mapper.projections.EventProjection;
import com.example.goodToKnow.repository.EventRepository;
import com.example.goodToKnow.service.notification.NotificationService;

import java.util.ArrayList;

@Service
public class EventService {

  @Autowired
  EventRepository eventRepository;

  @Autowired
  private NotificationService notificationService;

  public List<Event> getEventsByTimeInterval(LocalDateTime startOfDay, LocalDateTime endOfDay) {
    return eventRepository.findByStartsAtBetween(startOfDay, endOfDay);
  }

  public List<EventOut> getEventsByMonth(int year, int month) {
    List<EventOut> result = new ArrayList<>();
    List<EventProjection> data = eventRepository.findByMonth(year, month);

    for (EventProjection eventProjection : data) {
      long id = eventProjection.getId();
      String subject = eventProjection.getSubject();
      String teacher = eventProjection.getTeacher();
      LocalDateTime startsAt = eventProjection.getStarts_At();
      LocalDateTime finishesAt = eventProjection.getFinishes_At();
      String building = eventProjection.getBuilding();
      String classroom = eventProjection.getClassroom();
      String link = eventProjection.getLink();
      String comments = eventProjection.getComments();

      EventOut eventOut = new EventOut(id, subject, teacher, startsAt, finishesAt, building, classroom, link, comments);
      result.add(eventOut);
    }

    return result;
  }

  public EventOut saveEvent(EventIn eventIn) {
    Event event = eventRepository.save(
        new Event(eventIn.getSubject(), eventIn.getTeacher(), eventIn.getStartsAt(), eventIn.getFinishesAt(),
            eventIn.getBuilding(), eventIn.getClassroom(), eventIn.getLink(), eventIn.getComments()));

    EventOut eventOut = new EventOut(event.getId(), event.getSubject(), event.getTeacher(), event.getStartsAt(),
        event.getFinishesAt(),
        event.getBuilding(), event.getClassroom(), event.getLink(), event.getComments());

    return eventOut;
  }

  public void delete(Long id) {
    Optional<Event> event = eventRepository.findById(id);
    if (event.isEmpty()) {
      throw new EventNotFoundException();
    }
    eventRepository.deleteById(id);
  }

  public Event editEvent(Long id, EventIn eventIn) throws Exception {
    Optional<Event> eventToEdit = eventRepository.findById(id);
    if (eventToEdit.isEmpty()) {
      throw new EventNotFoundException();
    }

    Event eventToSave = eventToEdit.get();
    eventToSave.setTeacher(eventIn.getTeacher());
    eventToSave.setSubject(eventIn.getSubject());
    eventToSave.setStartsAt(eventIn.getStartsAt());
    eventToSave.setFinishesAt(eventIn.getFinishesAt());
    eventToSave.setBuilding(eventIn.getBuilding());
    eventToSave.setClassroom(eventIn.getClassroom());
    eventToSave.setLink(eventIn.getLink());
    eventToSave.setComments(eventIn.getComments());

    Event result = eventRepository.save(eventToSave);

    if (eventIn.isNotification()) {
      StringBuilder messageBuilder = new StringBuilder();
      messageBuilder.append("Evento editado: ").append(eventIn.getSubject());

      notificationService.sendTelegramNotification(messageBuilder.toString());
    }
    return result;
  }
}
