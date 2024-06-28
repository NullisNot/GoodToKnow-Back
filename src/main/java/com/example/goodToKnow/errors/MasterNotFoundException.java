package com.example.goodToKnow.errors;

public class MasterNotFoundException extends RuntimeException {
  public MasterNotFoundException() {
    super("Master not found");
  }
}
