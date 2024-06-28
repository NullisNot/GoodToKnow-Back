package com.example.goodToKnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Subject;
import com.example.goodToKnow.repository.SubjectRepository;

@Service
public class SubjectService {
  @Autowired
  SubjectRepository subjectRepository;

  public Subject saveSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  public List<Subject> findSubjects() {
    List<Subject> subjects = subjectRepository.findAll();

    return subjects;
  }

}
