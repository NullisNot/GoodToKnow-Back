package com.example.goodToKnow.errors;

public class TeacherNotFoundException extends RuntimeException {
  public TeacherNotFoundException() {
    super("Teacher not found");
  }
}
