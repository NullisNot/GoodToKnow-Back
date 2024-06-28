package com.example.goodToKnow.errors;

public class SubjectNotFoundException extends RuntimeException {
  public SubjectNotFoundException() {
    super("Subject not found");
  }
}
