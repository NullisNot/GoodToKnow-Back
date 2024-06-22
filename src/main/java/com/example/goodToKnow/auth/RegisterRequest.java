package com.example.goodToKnow.auth;

import com.example.goodToKnow.adminUser.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  String username;
  String password;
  String firstname;
  String lastname;
  String email;
  Role role;
}
