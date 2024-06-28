package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
