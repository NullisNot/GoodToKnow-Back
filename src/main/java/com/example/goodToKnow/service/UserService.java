package com.example.goodToKnow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUser(Long id) {
    return userRepository.findById(id);
  }

  public List<User> getUserByUserName(String UserName) {
    return userRepository.findByUserName(UserName);
  }

  public void saveOrUpdate(User user) {
    userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

}
