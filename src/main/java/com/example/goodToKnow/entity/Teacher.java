package com.example.goodToKnow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "firstname", nullable = false, length = 500)
  private String firstname;

  @Column(name = "lastname", nullable = false, length = 500)
  private String lastname;

  @Column(name = "email", nullable = false, length = 500)
  private String email;
}
