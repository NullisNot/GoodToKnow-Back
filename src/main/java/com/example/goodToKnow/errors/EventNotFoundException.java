package com.example.goodToKnow.errors;

public class EventNotFoundException extends RuntimeException {
  public EventNotFoundException() {
    super("Event not found");
  }
}
