package com.example.goodToKnow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.Master;
import com.example.goodToKnow.service.MasterService;

@RestController()
@RequestMapping(path = "api/v1/masters")
public class MasterController {
  @Autowired
  private MasterService masterService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Master> getMasters() {
    return masterService.findMasters();
  }

}
