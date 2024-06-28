package com.example.goodToKnow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Subject;
import com.example.goodToKnow.errors.SubjectNotFoundException;
import com.example.goodToKnow.service.SubjectService;

@RestController()
@RequestMapping(path = "api/v1/subjects")
@CrossOrigin(origins = { "http://localhost:4200" })
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Subject> getSubjects() {
    return subjectService.findSubjects();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Subject createSubject(@RequestBody Subject subject) {
    return subjectService.saveSubject(subject);
  }

  @DeleteMapping("/{subjectId}")
  public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("subjectId") Long subjectId) {
    try {
      subjectService.deleteSubject(subjectId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (SubjectNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
