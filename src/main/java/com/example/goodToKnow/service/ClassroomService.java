package com.example.goodToKnow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Classroom;
import com.example.goodToKnow.errors.ClassroomNotFoundException;
import com.example.goodToKnow.repository.ClassroomRepository;

@Service
public class ClassroomService {

  @Autowired
  ClassroomRepository classroomRepository;

  public Classroom saveClassroom(Classroom classroom) {
    return classroomRepository.save(classroom);
  }

  public List<Classroom> getClassrooms() {
    List<Classroom> classrooms = classroomRepository.findAll();

    return classrooms;
  }

  public void deleteClassroom(Long id) {
    Optional<Classroom> classroom = classroomRepository.findById(id);

    if (classroom.isEmpty()) {
      throw new ClassroomNotFoundException();
    }
    classroomRepository.deleteById(id);
  }

}
