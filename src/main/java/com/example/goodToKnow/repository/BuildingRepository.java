package com.example.goodToKnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {

}
