package com.example.goodToKnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public void saveOrUpdate(User user) {
    userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

}
