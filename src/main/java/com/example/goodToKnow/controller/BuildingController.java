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

import com.example.goodToKnow.entity.Building;
import com.example.goodToKnow.errors.BuildingNotFoundException;
import com.example.goodToKnow.service.BuildingService;

@RestController()
@RequestMapping(path = "api/v1/buildings")
@CrossOrigin(origins = { "http://localhost:4200" })
public class BuildingController {
  @Autowired
  private BuildingService buildingService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Building> getBuildings() {
    return buildingService.findBuildings();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Building createBuilding(@RequestBody Building building) {
    return buildingService.saveBuilding(building);
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> deleteBuilding(@PathVariable("buildingId") Long buildingId) {
    try {
      buildingService.deleteBuilding(buildingId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (BuildingNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
