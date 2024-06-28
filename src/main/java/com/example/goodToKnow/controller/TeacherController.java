package com.example.goodToKnow.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Teacher;
import com.example.goodToKnow.service.TeacherService;

@RestController()
@RequestMapping(path = "api/v1/teachers")
@CrossOrigin(origins = { "http://localhost:4200" })
public class TeacherController {
  @Autowired
  private TeacherService teacherService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Teacher> getTeachers() {
    return teacherService.findTeachers();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Teacher createTeacher(@RequestBody Teacher teacher) {
    return teacherService.saveTeacher(teacher);
  }
}
