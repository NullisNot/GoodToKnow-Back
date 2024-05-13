package com.example.goodToKnow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.service.EventService;

import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;

@RestController()
@RequestMapping(path = "api/v1/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Event> getEventsByDay(
      @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);

    return eventService.getEventsByTimeInterval(startOfDay, endOfDay);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EventOut createEvent(@RequestBody EventIn eventIn) {
    EventOut eventOut = eventService.saveEvent(eventIn);

    return eventOut;
  }

  @DeleteMapping("/{eventId}")
  public void deleteEvent(@PathVariable("eventId") Long eventId) {
    eventService.delete(eventId);
  }
}
