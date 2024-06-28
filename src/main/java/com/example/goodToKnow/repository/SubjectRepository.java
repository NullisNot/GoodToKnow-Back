package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
