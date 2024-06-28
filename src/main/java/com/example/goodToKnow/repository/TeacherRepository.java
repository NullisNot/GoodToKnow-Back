package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
