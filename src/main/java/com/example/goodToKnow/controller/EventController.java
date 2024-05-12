package com.example.goodToKnow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.service.EventService;

@RestController()
@RequestMapping(path = "api/v1/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping()
  public List<Event> getEventsByDay(
      @RequestParam(name = "day(yyyy-mm-dd)") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);

    return eventService.getEventsByTimeInterval(startOfDay, endOfDay);
  }
}
