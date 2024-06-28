package com.example.goodToKnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Teacher;
import com.example.goodToKnow.repository.TeacherRepository;

@Service
public class TeacherService {
  @Autowired
  TeacherRepository teacherRepository;

  public Teacher saveTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  public List<Teacher> findTeachers() {
    List<Teacher> teachers = teacherRepository.findAll();

    return teachers;
  }

}
