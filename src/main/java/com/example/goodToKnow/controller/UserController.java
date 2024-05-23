package com.example.goodToKnow.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.errors.UserNotFoundException;
import com.example.goodToKnow.mapper.in.UserIn;
import com.example.goodToKnow.mapper.out.UserOut;
import com.example.goodToKnow.service.UserService;

@RestController()
@RequestMapping(path = "api/v1/adminUsers")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserOut createUser(@RequestBody UserIn userIn) {
    UserOut userOut = userService.saveUser(userIn);
    return userOut;
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
    try {
      userService.delete(userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UserNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
