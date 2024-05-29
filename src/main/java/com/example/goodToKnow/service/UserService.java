package com.example.goodToKnow.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.errors.UserNotFoundException;
import com.example.goodToKnow.mapper.in.UserIn;
import com.example.goodToKnow.mapper.out.UserOut;
import com.example.goodToKnow.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public UserOut saveUser(UserIn userIn) {
    User user = userRepository.save(new User(userIn.getUserName(), userIn.getPassword(), userIn.getEmail()));

    UserOut userOut = new UserOut(user.getId(), user.getUserName(), user.getEmail());

    return userOut;
  }

  public void delete(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (Optional.empty().equals(user)) {
      throw new UserNotFoundException();
    }
    userRepository.deleteById(id);
  }

}
