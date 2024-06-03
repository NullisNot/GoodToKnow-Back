package com.example.goodToKnow.mapper.projections;

import java.time.LocalDateTime;


public interface EventProjection {
    long getId();
    String getSubject();
    String getTeacher();
    LocalDateTime getStarts_At();
    LocalDateTime getFinishes_At();
    String getBuilding();
    String getClassroom();
    String getLink();
    String getComments();
}
