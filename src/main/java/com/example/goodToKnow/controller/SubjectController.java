package com.example.goodToKnow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Subject;
import com.example.goodToKnow.service.SubjectService;

@RestController()
@RequestMapping(path = "api/v1/subjects")
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Subject> getSubjects() {
    return subjectService.findSubjects();
  }

}
