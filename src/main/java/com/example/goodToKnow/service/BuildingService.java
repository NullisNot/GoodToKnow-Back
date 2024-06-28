package com.example.goodToKnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Building;
import com.example.goodToKnow.repository.BuildingRepository;

@Service
public class BuildingService {
  @Autowired
  BuildingRepository buildingRepository;

  public Building saveBuilding(Building building) {
    return buildingRepository.save(building);
  }

  public List<Building> findBuildings() {
    List<Building> buildings = buildingRepository.findAll();

    return buildings;
  }

}
