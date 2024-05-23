package com.example.goodToKnow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodToKnow.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(Long id);

  void delete(User user);
}
