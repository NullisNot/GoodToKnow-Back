package com.example.goodToKnow.errors;

public class BuildingNotFoundException extends RuntimeException {
  public BuildingNotFoundException() {
    super("Building not found");
  }
}
