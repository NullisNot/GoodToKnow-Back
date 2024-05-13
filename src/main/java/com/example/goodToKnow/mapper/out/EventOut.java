package com.example.goodToKnow.mapper.out;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventOut {
  @NonNull
  private String subject;

  @NonNull
  private String teacher;

  @NonNull
  private LocalDateTime startsAt;

  @NonNull
  private LocalDateTime finishesAt;

  @NonNull
  private String building;

  @NonNull
  private String classroom;

  @NonNull
  private String link;

  @NonNull
  private String comments;
}
