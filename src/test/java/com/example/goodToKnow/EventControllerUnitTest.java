package com.example.goodToKnow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.goodToKnow.controller.EventController;
import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.errors.EventNotFoundException;
import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.repository.EventRepository;
import com.example.goodToKnow.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(EventController.class)
public class EventControllerUnitTest {

  @MockBean
  private EventRepository eventRepository;

  @MockBean
  private EventService eventService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shoudCreateEvent() throws Exception {
    Event event = createEvent();

    mockMvc.perform(post("/api/v1/events").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(event)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldUpdateEvent() throws Exception {
    Event event = createEvent();
    Optional<Event> responseEvent = Optional.of((Event) event);
    
    when(eventRepository.findById(event.getId())).thenReturn(responseEvent);
    
    EventIn eventIn = createEventIn();
    eventIn.setTeacher("new teacher");
    String url = String.format("/api/v1/events/%s", event.getId());
    mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(eventIn)))
        .andExpect(status().isOk());
  }
  
  @Test
  void shouldNotUpdateEvent() throws Exception {
    Event event = createEvent();    
    when(eventRepository.findById(event.getId())).thenReturn(Optional.empty());
    
    EventIn eventIn = createEventIn();
    eventIn.setTeacher("new teacher");
    
    doThrow(new EventNotFoundException()).when(eventService).editEvent(event.getId(), eventIn);
    
    String url = String.format("/api/v1/events/%s", event.getId());
    mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(eventIn)))
        .andExpect(status().isNotFound());
  }

  @Test
  void shouldDeleteEventById() throws Exception {
    Event event = createEvent();
    Optional<Event> responseEvent = Optional.of((Event) event);

    when(eventRepository.findById(event.getId())).thenReturn(responseEvent);
    String url = String.format("/api/v1/events/%s", event.getId());
    mockMvc.perform(delete(url)).andExpect(status().isOk());
  }

  @Test
  void shouldNotDeleteEvent() throws Exception {
    long eventId = 123;

    when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
    doThrow(new EventNotFoundException()).when(eventService).delete(eventId);
    String url = String.format("/api/v1/events/%s", eventId);
    mockMvc.perform(delete(url)).andExpect(status().isNotFound());
  }

  @Test
  void shouldGetEventByDay() throws Exception {
    Event event = createEvent();
    String eventDate = "2024-05-11";

    List<Event> events = new ArrayList<>();
    events.add(event);

    when(eventRepository.findByStartsAtBetween(event.getStartsAt(), event.getFinishesAt())).thenReturn(events);

    String url = String.format("/api/v1/events?day=%s", eventDate);

    mockMvc.perform(get(url))
        .andExpect(status().isOk());
  }

  private Event createEvent() {
    Event event = new Event();
    event.setId((long) 1);
    event.setSubject("Desarrollo de interfaces de usuario");
    event.setTeacher("JP");
    LocalDateTime starts = LocalDateTime.of(2024, 5, 11, 16, 00);
    event.setStartsAt(starts);
    LocalDateTime finish = LocalDateTime.of(2024, 5, 11, 21, 00);
    event.setFinishesAt(finish);
    event.setBuilding("Digital Hub");
    event.setClassroom("Aula 5");
    event.setLink("url.example");
    event.setComments("Clase de desarrollo aplicaciones usuario con nextJs");

    return event;
  }
  
  private EventIn createEventIn() {
    EventIn event = new EventIn();
    event.setSubject("Desarrollo de interfaces de usuario");
    event.setTeacher("JP");
    LocalDateTime starts = LocalDateTime.of(2024, 5, 11, 16, 00);
    event.setStartsAt(starts);
    LocalDateTime finish = LocalDateTime.of(2024, 5, 11, 21, 00);
    event.setFinishesAt(finish);
    event.setBuilding("Digital Hub");
    event.setClassroom("Aula 5");
    event.setLink("url.example");
    event.setComments("Clase de desarrollo aplicaciones usuario con nextJs");

    return event;
  }

}
