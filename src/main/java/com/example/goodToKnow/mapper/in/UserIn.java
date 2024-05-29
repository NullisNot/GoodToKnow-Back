package com.example.goodToKnow.mapper.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserIn {

  public UserIn(String userName, String password, String email) {
    this.userName = userName;
    this.password = password;
    this.email = email;
  }

  @NotNull
  private String userName;

  @NotNull
  private String password;

  @NotNull
  private String email;
}
