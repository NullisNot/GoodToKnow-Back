package com.example.goodToKnow.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.entity.Notification;
import com.example.goodToKnow.errors.EventNotFoundException;
import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;
import com.example.goodToKnow.mapper.projections.EventProjection;
import com.example.goodToKnow.repository.EventRepository;
import com.example.goodToKnow.service.notification.TelegramNotificationService;

import java.util.ArrayList;

@Service
public class EventService {

  @Autowired
  EventRepository eventRepository;

  @Autowired
  TelegramNotificationService telegramNotificationService;

  @Autowired
  NotificationService notificationService;

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

    String message = buildMessage(eventIn, "creado");

    Notification notification = new Notification();
    notification.setNotification(message);

    if (eventIn.isNotification()) {
      telegramNotificationService.sendTelegramNotification(message);
      notificationService.saveNotification(notification);
    }

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
    String message = buildMessage(eventIn, "editado");
    Notification notification = new Notification();
    notification.setNotification(message);

    if (eventIn.isNotification()) {
      telegramNotificationService.sendTelegramNotification(message);
      notificationService.saveNotification(notification);
    }
    return result;
  }

  private static String buildMessage(EventIn eventIn, String action) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    String formattedDate = eventIn.getStartsAt().toLocalDate().format(dateFormatter);
    String formattedStartsAt = eventIn.getStartsAt().toLocalTime().format(timeFormatter);
    String formattedFinishesAt = eventIn.getFinishesAt().toLocalTime().format(timeFormatter);

    String eventStatus = "creado".equalsIgnoreCase(action) ? "🆕 ¡Nuevo evento! 🆕"
        : "📢 ¡Se han registrado cambios! 📢";

    String message = String.format(
        "%s\n\n" +
            " - 📌 Día: %s\n" +
            " - 👨‍🏫 Docente: %s\n" +
            " - 📚 Asignatura: %s\n" +
            " - 🕓 Horario: %s/%s\n" +
            " - 🏢 Ubicación: %s, %s\n" +
            " - 🔗 Enlace: %s\n" +
            " - 📝 Comentarios: %s\n",
        eventStatus,
        formattedDate,
        eventIn.getTeacher(),
        eventIn.getSubject(),
        formattedStartsAt,
        formattedFinishesAt,
        eventIn.getBuilding(),
        eventIn.getClassroom(),
        eventIn.getLink(),
        eventIn.getComments());

    return message;
  }
}
