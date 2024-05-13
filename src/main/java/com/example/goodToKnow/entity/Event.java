package com.example.goodToKnow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
  public Event() {

  }

    public Event(String subject, String teacher, LocalDateTime startsAt, LocalDateTime finishesAt, String building, String classroom, String link, String comments) {
        
        this.subject = subject;
        this.teacher = teacher;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
        this.building = building;
        this.classroom = classroom;
        this.link = link;
        this.comments = comments;
    }

  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "subject", nullable = false, length = 500)
  private String subject;

  @Column(name = "teacher", nullable = false, length = 500)
  private String teacher;

  @Column(name = "startsAt", nullable = false, length = 25)
  private LocalDateTime startsAt;

  @Column(name = "finishesAt", nullable = false, length = 25)
  private LocalDateTime finishesAt;

  @Column(name = "building", nullable = false, length = 500)
  private String building;

  @Column(name = "classroom", nullable = false, length = 500)
  private String classroom;

  @Column(name = "link", nullable = false, length = 500)
  private String link;

  @Column(name = "comments", nullable = false, length = 500)
  private String comments;

}
