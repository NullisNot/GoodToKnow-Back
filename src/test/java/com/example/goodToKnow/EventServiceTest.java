package com.example.goodToKnow;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.errors.EventNotFoundException;
import com.example.goodToKnow.mapper.in.EventIn;
import com.example.goodToKnow.mapper.out.EventOut;
import com.example.goodToKnow.repository.EventRepository;
import com.example.goodToKnow.service.EventService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventsByTimeInterval() {
        LocalDateTime startOfDay = LocalDateTime.of(2024, 6, 1, 0, 0);
        LocalDateTime endOfDay = LocalDateTime.of(2024, 6, 1, 23, 59);
        List<Event> events = Arrays.asList(
                new Event("Subject1", "Teacher1", startOfDay.plusHours(1), startOfDay.plusHours(2), "Building1", "Classroom1", "Link1", "Comments1"),
                new Event("Subject2", "Teacher2", startOfDay.plusHours(3), startOfDay.plusHours(4), "Building2", "Classroom2", "Link2", "Comments2")
        );

        when(eventRepository.findByStartsAtBetween(startOfDay, endOfDay)).thenReturn(events);

        List<Event> result = eventService.getEventsByTimeInterval(startOfDay, endOfDay);

        assertEquals(2, result.size());
        verify(eventRepository, times(1)).findByStartsAtBetween(startOfDay, endOfDay);
    }

    @Test
    public void testSaveEvent() {
        EventIn eventIn = createEventIn();
        Event savedEvent = new Event("Subject1", "Teacher1", eventIn.getStartsAt(), eventIn.getFinishesAt(), "Building1", "Classroom1", "Link1", "Comments1");
        savedEvent.setId(1L);

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        EventOut result = eventService.saveEvent(eventIn);

        assertNotNull(result);
        assertEquals(savedEvent.getId(), result.getId());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testDeleteEvent() {
        Long eventId = 1L;
        Event event = createEvent();
        event.setId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        eventService.delete(eventId);

        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    public void testDeleteEventNotFound() {
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.delete(eventId));

        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testEditEvent() {
        Long eventId = 1L;
        EventIn eventIn = createEventIn();
        Event existingEvent = new Event("Subject1", "Teacher1", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Building1", "Classroom1", "Link1", "Comments1");
        existingEvent.setId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(Event.class))).thenReturn(existingEvent);

        Event result = eventService.editEvent(eventId, eventIn);

        assertNotNull(result);
        assertEquals(eventIn.getSubject(), result.getSubject());
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(existingEvent);
    }

    @Test
    public void testEditEventNotFound() {
        Long eventId = 1L;
        EventIn eventIn = createEventIn();
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.editEvent(eventId, eventIn));

        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, never()).save(any(Event.class));
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