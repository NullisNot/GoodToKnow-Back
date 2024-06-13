package com.example.goodToKnow.mapper.in;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventIn {
  private String subject;
  private String teacher;
  private LocalDateTime startsAt;
  private LocalDateTime finishesAt;
  private String building;
  private String classroom;
  private String link;
  private String comments;
  private boolean notification;
}
