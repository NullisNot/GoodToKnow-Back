package com.example.goodToKnow.mapper.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserIn {
  @NotNull
  private String userName;

  @NotNull
  private String password;

  @NotNull
  private String email;
}
