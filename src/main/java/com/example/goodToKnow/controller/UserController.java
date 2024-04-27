package com.example.goodToKnow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.service.UserService;

@RestController()
@RequestMapping(path = "api/v1/adminUsers")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAll() {
    return userService.getUsers();
  }

  @GetMapping("/id/{userId}")
  public Optional<User> getById(@PathVariable("userId") Long userId) {
    return userService.getUser(userId);
  }

  @GetMapping("/name/{userName}")
  public List<User> getByName(@PathVariable("userName") String userName) {
    return userService.getUserByUserName(userName);
  }

  @PostMapping
  public User saveUpdate(@RequestBody User user) {
    userService.saveOrUpdate(user);
    return user;
  }

  @DeleteMapping("/{userId}")
  public void delete(@PathVariable("userId") Long userId) {
    userService.delete(userId);
  }

}
