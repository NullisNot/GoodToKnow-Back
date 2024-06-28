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

import com.example.goodToKnow.entity.Master;
import com.example.goodToKnow.errors.MasterNotFoundException;
import com.example.goodToKnow.service.MasterService;

@RestController()
@RequestMapping(path = "api/v1/masters")
@CrossOrigin(origins = { "http://localhost:4200" })
public class MasterController {
  @Autowired
  private MasterService masterService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Master> getMasters() {
    return masterService.findMasters();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Master createMaster(@RequestBody Master master) {
    return masterService.saveMaster(master);
  }

  @DeleteMapping("/{masterId}")
  public ResponseEntity<HttpStatus> deleteMaster(@PathVariable("masterId") Long masterId) {
    try {
      masterService.deleteMaster(masterId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (MasterNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
