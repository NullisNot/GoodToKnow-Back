package com.example.goodToKnow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Classroom;
import com.example.goodToKnow.service.ClassroomService;

@RestController()
@RequestMapping(path = "api/v1/classrooms")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClassroomController {
  @Autowired
  private ClassroomService classroomService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Classroom> getClassrooms() {
    return classroomService.getClassrooms();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Classroom createClassroom(@RequestBody Classroom classroom) {
    return classroomService.saveClassroom(classroom);
  }

}
