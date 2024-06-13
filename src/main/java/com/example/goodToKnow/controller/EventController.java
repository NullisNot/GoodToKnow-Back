package com.example.goodToKnow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.errors.EventNotFoundException;
import com.example.goodToKnow.service.EventService;

import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;

@RestController()
@RequestMapping(path = "api/v1/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Event> getEventsByDay(
      @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);

    return eventService.getEventsByTimeInterval(startOfDay, endOfDay);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{year}/{month}")
  @ResponseStatus(HttpStatus.OK)
  public List<EventOut> getEventsByMonth(@PathVariable(name = "year") int year,
      @PathVariable(name = "month") int month) {

    List<EventOut> result = eventService.getEventsByMonth(year, month);
    return result;
  }

  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ResponseStatus(HttpStatus.CREATED)
  public EventOut createEvent(@RequestBody EventIn eventIn) {
    EventOut eventOut = eventService.saveEvent(eventIn);

    return eventOut;
  }

  @DeleteMapping("/{eventId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("eventId") Long eventId) {
    try {
      eventService.delete(eventId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (EventNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping("/{eventId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Event> editEvent(@PathVariable("eventId") Long eventId, @RequestBody EventIn eventIn) {
    try {
      Event eventEdited = eventService.editEvent(eventId, eventIn);
      return new ResponseEntity<>(eventEdited, HttpStatus.OK);
    } catch (EventNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
