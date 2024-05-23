package com.example.goodToKnow.mapper.out;

import lombok.Data;

@Data
public class UserOut {
  private long id;
  private String userName;
  private String email;

  public UserOut() {
  }

  public UserOut(long id, String userName, String email) {
    this.id = id;
    this.userName = userName;
    this.email = email;
  }
}
