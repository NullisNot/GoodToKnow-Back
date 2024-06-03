package com.example.goodToKnow.mapper.out;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class EventOut {
  
  private long id; 
  private String subject;
  private String teacher;
  private LocalDateTime startsAt;
  private LocalDateTime finishesAt;
  private String building;
  private String classroom;
  private String link;
  private String comments;

    public EventOut(long id, String subject, String teacher, LocalDateTime startsAt, LocalDateTime finishesAt, String building, String classroom, String link, String comments) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
        this.building = building;
        this.classroom = classroom;
        this.link = link;
        this.comments = comments;
    }
  
  
}
