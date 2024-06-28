package com.example.goodToKnow.errors;

public class ClassroomNotFoundException extends RuntimeException {
  public ClassroomNotFoundException() {
    super("Classroom not found");
  }
}
