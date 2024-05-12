package com.example.goodToKnow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.repository.EventRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class EventRepositoryTests {

  @Autowired
  private EventRepository repository;

  @Test
  public void shouldGetEventByDay() {
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

    Event eventSaved = repository.save(event);
    List<Event> testEvent = repository.findByStartsAtBetween(starts, finish);

    assertEquals(eventSaved, testEvent.get(0));

  }

}
