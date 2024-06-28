package com.example.goodToKnow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Classroom;
import com.example.goodToKnow.errors.ClassroomNotFoundException;
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

  @DeleteMapping("/{classroomId}")
  public ResponseEntity<HttpStatus> deleteClassroom(@PathVariable("classroomId") Long classroomId) {
    try {
      classroomService.deleteClassroom(classroomId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (ClassroomNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
