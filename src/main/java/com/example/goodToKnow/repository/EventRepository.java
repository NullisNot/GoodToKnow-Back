package com.example.goodToKnow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Event;
import com.example.goodToKnow.mapper.projections.EventProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByStartsAtBetween(LocalDateTime start, LocalDateTime end);
  
  @Query(value = "SELECT * FROM events e WHERE MONTH(e.starts_at) = :month AND YEAR(e.starts_at) = :year", nativeQuery = true)
  List<EventProjection> findByMonth(@Param("year") int year, @Param("month") int month);
}
