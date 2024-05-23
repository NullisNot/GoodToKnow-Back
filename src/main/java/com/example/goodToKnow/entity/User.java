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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false, length = 20)
  private String userName;

  @Column(name = "password", nullable = false, length = 64)
  private String password;

  @Column(name = "email_adress", unique = true, nullable = false)
  private String email;

  public User() {
  }

  public User(String userName, String password, String email) {
    this.userName = userName;
    this.password = password;
    this.email = email;
  }
}
